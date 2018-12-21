package application.dataClass;

import com.jfoenix.controls.JFXCheckBox;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;

public class CheckBoxTCell<S, T> extends TableCell<S, T> {
	private final JFXCheckBox chebox;
	private ObservableValue<T> ov;

	public CheckBoxTCell() {
		this.chebox = new JFXCheckBox();
		// ���Ԫ��
		setGraphic(chebox);
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
			chebox.setSelected(false);
			setGraphic(chebox);
		}
	}
}
