public class Transformation2d {
	// �ϊ��p�����[�^ a,b,c,d
	private double a = 0.0;
	private double b = 0.0;
	private double c = 0.0;
	private double d = 0.0;

	// �p�����[�^���v�Z
	boolean set( double x1, double y1, double x2, double y2,
				double px1, double py1, double px2, double py2 )
	{
		// �[���Ƃ݂Ȃ��l
		double e = 0.0000000001;

		// �����l����
		a = b = c = d = 0.0;

		// x1��x2�������l�ł���΃G���[
		if ( e > Math.abs( x1 - x2 ) ) return false;

		// y1��y2�������l�ł���΃G���[
		if ( e > Math.abs( y1 - y2 ) ) return false;

		// �p�����[�^�̌v�Z
		a = ( px1 - px2 ) / ( x1 - x2 );
		b = ( px1 * x2 - px2 * x1 ) / ( x2 - x1 );
		c = ( py1 - py2 ) / ( y1 - y2 );
		d = ( py1 * y2 - py2 * y1 ) / ( y2 - y1 );

		return true;
	}


	// �ϊ����x���W���擾
	public double getX( double x )
	{
		return a * x + b;
	}

	// �ϊ����y���W���擾
	public double getY( double y )
	{
		return c * y + d;
	}
}