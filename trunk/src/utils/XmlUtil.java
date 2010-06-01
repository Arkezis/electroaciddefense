package utils;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.util.Log;

public class XmlUtil {

	public static String TAG = XmlUtil.class.getName();

	public static Node getChildNodeFromName(Node node, String name) {
		if (node == null || name == null)
			return null;
		return getChildNodeFromName(node.getChildNodes(), name);
	}

	public static Node getChildNodeFromName(NodeList nodeList, String name) {
		if (name == null)
			return null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (name.equals(nodeList.item(i).getNodeName()))
				return nodeList.item(i);
		}
		return null;
	}

	public static String getAttributeFromNode(Node currentNode, String attributeName) {
		if (currentNode == null || attributeName == null || attributeName.length() == 0)
			return null;
		NamedNodeMap attributes = currentNode.getAttributes();
		if (attributes != null) {
			Node attribut = attributes.getNamedItem(attributeName);
			if (attribut !=null){
				return attribut.getNodeValue();
			}
		}
		return null;
	}

	public static int getAttributeIntFromNode(Node currentNode, String attributeName) {
		return Integer.parseInt(getAttributeFromNode(currentNode, attributeName));
	}

	public static boolean getAttributeBooleanFromNode(Node currentNode, String attributeName) {
		return Boolean.parseBoolean(getAttributeFromNode(currentNode, attributeName));
	}

	public static Document getDocumentFromResource(Context context, int xmlResourceId) throws Exception {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(context.getResources().openRawResource(xmlResourceId));

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}
	
	public static float getAttributeFloatFromNode(Node currentNode, String attributeName) {
		return Float.parseFloat(getAttributeFromNode(currentNode, attributeName));
	}
}