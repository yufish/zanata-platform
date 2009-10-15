package org.fedorahosted.flies.webtrans.client.rpc;


import java.util.ArrayList;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import org.fedorahosted.flies.gwt.model.DocumentId;
import org.fedorahosted.flies.gwt.model.TransUnit;
import org.fedorahosted.flies.gwt.rpc.GetTransUnits;
import org.fedorahosted.flies.gwt.rpc.GetTransUnitsResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DummyDispatchAsync extends SeamDispatchAsync {
	public DummyDispatchAsync() {
		Log.info("DummyDispatchAsync()");
	}

	@Override
	public <A extends Action<R>, R extends Result> void execute(final A action,
			final AsyncCallback<R> callback) {

		if (action instanceof GetTransUnits) {
			final GetTransUnits gtuAction = (GetTransUnits) action;
			DeferredCommand.addCommand(new GetTransUnitCommand<R>(gtuAction, callback));
//		} else if (action instanceof GetDocList) {
//			
		}
	}
	
	private static final class GetTransUnitCommand<R> implements Command {
		private final GetTransUnits gtuAction;
		private final AsyncCallback<R> callback;

		private GetTransUnitCommand(GetTransUnits gtuAction,
				AsyncCallback<R> callback) {
			this.gtuAction = gtuAction;
			this.callback = callback;
		}

		@Override
		public void execute() {
			DocumentId documentId = gtuAction.getDocumentId();
			int count = gtuAction.getCount();
			int offset = gtuAction.getOffset();
			int totalCount = count * 5;
			GetTransUnitsResult result = new GetTransUnitsResult(
					documentId, 
					generateTransUnitSampleData(count, offset), 
					totalCount);
			callback.onSuccess((R) result);
		}

		private ArrayList<TransUnit> generateTransUnitSampleData(int numRows, int start) {
			ArrayList<TransUnit> units = new ArrayList<TransUnit>();
			for(int i=start;i<start+numRows; i++) {
				TransUnit unit = new TransUnit("<hellow num=\"" + (i+1) + "\" />", "<world> \"" + (i+1) +"\"</world>");
				unit.setFuzzy(Math.random() > 0.7);
				units.add(unit);
			}
			return units;
		}
		
	}

}
