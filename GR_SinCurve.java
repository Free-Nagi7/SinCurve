import java.awt.*;
import javax.swing.*;

public class GR_SinCurve {
	public static void main(String[] args) {
		// �t���[���̑傫����ݒ�
		int w  = 400;
		int h = 320;

		// �t���[�����쐬
		JFrame frame = new JFrame();

		// // �^�C�g������ݒ�
		frame.setTitle( "LF" );


		// �����t���[���̑傫����ݒ�
		frame.getContentPane().setPreferredSize( new Dimension( w, h ) );
		frame.pack();

		// �h�~�h�{�^�������������̏�����ݒ�
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// �t���[���Ƀp�l����ǉ�
		MyPanel panel = new MyPanel();
		frame.getContentPane().add( panel );

		// �t���[����\��
		frame.setVisible( true );
	}
}


// JPanel���p������MyPanel���쐬
class MyPanel extends JPanel {
	public MyPanel() {
		// �w�i�F����(black)�ɐݒ�
		setBackground( Color.white );
	}

	// �֐�
	private double f( double x )
	{
		// �p�xdeg�����W�A��rad�ɕϊ�
		double rad = Math.toRadians( (double)x );
		// �T�C�����v�Z
		return Math.sin( rad );
	}


	// ���C���̊J�n���W
	private int line_x1 = 0;
	private int line_y1 = 0;

	// ���C���̊J�n���W�̐ݒ�
	private void moveTo(Graphics g, int x, int y )
	{
		line_x1 = x;
		line_y1 = y;
	}

	// ���C���̕\��
	private void lineTo(Graphics g, int x, int y )
	{
		g.drawLine( line_x1, line_y1, x, y );
		line_x1 = x;
		line_y1 = y;
	}

	// �`��
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );

		// �t���[���̑傫�����擾
		int frame_w = getWidth();
		int frame_h = getHeight();

		// ���W�ϊ��N���X�̍쐬
		Transformation2d trans = new Transformation2d();

		// ���W��ݒ�
		// (-360.0,1.2)-(360.0,-1.2)�͈̔͂��t���[���S�̂ɕ\������
		trans.set( -360.0, 1.2, 360, -1.2, 0.0, 0.0,
			(double)( frame_w - 1 ), (double)( frame_h - 1 ) );

		// ���_�̉摜���W���擾
		double origin_x = trans.getX( 0.0 );
		double origin_y = trans.getY( 0.0 );

		// ���W���̐F����
		g.setColor( Color.black );

		// x���W�̕`��
		g.drawLine( 0, (int)origin_y, frame_w - 1, (int)origin_y );

		// y���W�̕`��
		g.drawLine( (int)origin_x, 0, (int)origin_x, frame_h - 1 );

		// �T�C��(sin)�J�[�u�̕`��
		// �T�C���J�[�u�̐F���
		g.setColor( Color.red );

		// �p�x-360����360�x�܂ł̃T�C���J�[�u��\��
		boolean isStart = true;
		for ( int x = -300; x <= 300; x++ ) {
			// �v�Z���ʂ�y�ɑ��
			double y = f( x );

			// ���w���W( deg, value )��
			// �摜���W( line_x2, line_x2 )�ɕϊ�
			int px = (int)trans.getX( (double)x );
			int py = (int)trans.getY( y );
			
			//
			if ( true == isStart ) {
				moveTo( g, px, py );
				isStart = false;
			}
			else
				lineTo( g, px, py );
		}
	}
}