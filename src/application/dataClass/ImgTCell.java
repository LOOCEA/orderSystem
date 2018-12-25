package application.dataClass;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

public class ImgTCell<S, T> extends TableCell<S, T> {
	private final ImageView img;
	private ObservableValue<T> ov;

	public ImgTCell() {
		this.img = new ImageView();
		// ���Ԫ��
		setGraphic(img);
	}

	@Override
	public void updateItem(T item, boolean empty) {
		System.out.println("empty��" + empty);
		super.updateItem(item, empty);
		if (empty) {
			// �������Ϊ��Ĭ�ϲ����Ԫ��
			setText(null);
			setGraphic(null);
		} else {
			// ��ʼ��Ϊ��ѡ��
			// img.setSelected(false);
			setGraphic(img);
		}
	}
}
