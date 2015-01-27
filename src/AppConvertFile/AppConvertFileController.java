package AppConvertFile;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;

public class AppConvertFileController extends GenericForwardComposer {
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

	}

	public void onUpload$btn(UploadEvent e)// throws InterruptedException
	{
		if (e.getMedias() != null) {
			System.out.println("entre a evento");
		}
	}

}
