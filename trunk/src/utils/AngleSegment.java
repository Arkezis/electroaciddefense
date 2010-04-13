package utils;

import java.nio.*;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import com.android.angle.AngleObject;
import com.android.angle.AngleVector;

public class AngleSegment extends AngleObject
{
	protected AngleVector mA;
	protected AngleVector mB;
	public float mRed;   //Red tint (0 - 1)
	public float mGreen;	//Green tint (0 - 1)
	public float mBlue;	//Blue tint (0 - 1)
	public float mAlpha;	//Alpha channel (0 - 1)
	private FloatBuffer mVertices;
	public AngleSegment (float x1, float y1, float x2, float y2)
	{
		mA = new AngleVector(x1, y1);
		mB = new AngleVector(x2, y2);
		mVertices = ByteBuffer.allocateDirect(2 * 2 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		mAlpha = 1;
		mRed = 1;
		mGreen = 0;
		mBlue =1;
		mVertices.put(0, 0);
		mVertices.put(1, 0);
		mVertices.put(2, mB.mX-mA.mX);
		mVertices.put(3, mB.mY-mA.mY);
	}

	public void draw(GL10 gl)
	{
		gl.glDisable(GL11.GL_TEXTURE_2D);
		gl.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glColor4f(mRed, mGreen, mBlue, mAlpha);
		gl.glTranslatef(mA.mX, mA.mY, 0.0f);
		gl.glVertexPointer(2, GL11.GL_FLOAT, 0, mVertices);
		gl.glDrawArrays(GL11.GL_LINES, 0, 2);
		gl.glPopMatrix();

		gl.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		gl.glEnable(GL11.GL_TEXTURE_2D);
		super.draw(gl);  //No childs
	}
}
