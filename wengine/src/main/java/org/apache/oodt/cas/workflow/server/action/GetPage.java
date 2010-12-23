/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.oodt.cas.workflow.server.action;

//OODT imports
import org.apache.oodt.cas.catalog.page.PageInfo;
import org.apache.oodt.cas.workflow.engine.WorkflowEngineClient;
import org.apache.oodt.cas.workflow.page.QueuePage;
import org.apache.oodt.cas.workflow.processor.ProcessorStub;

/**
 * @author bfoster
 * @version $Revision$
 *
 * <p>
 * Action for print out a page of queued up WorkflowProcessors
 * <p>
 */
public class GetPage extends FilteredAction {

	private int pageNum;
	private int pageSize;
	private boolean showMessage;
	
	public GetPage() {
		super();
		this.pageNum = 1;
		this.pageSize = 10;
		this.showMessage = false;
	}
	
	@Override
	public void performAction(WorkflowEngineClient weClient) throws Exception {
		PageInfo pageInfo = new PageInfo(pageSize, pageNum);
		QueuePage page = weClient.getPage(pageInfo, this.createFilter(weClient));
		System.out.println("Workflows " + getFilterAsString() + " (Page: " + page.getPageInfo().getPageNum() + "/" + page.getPageInfo().getTotalPages() +  "; Total: " + page.getPageInfo().getNumOfHits() + "):");
		for (ProcessorStub stub : page.getStubs()) {
			System.out.println("  - InstanceId = '" + stub.getInstanceId() + "', ModelId = '" + stub.getModelId() +"', State = '" + stub.getState().getName() + "'");
			if (this.showMessage)
				System.out.println("      (Message = '" + stub.getState().getMessage() + "')");
		}
		System.out.println();
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void showMessage(boolean showMessage) {
		this.showMessage = showMessage;
	}
	
	protected String getFilterAsString() {
		String filter = "[";
		if (this.stateName != null)
			filter += "state='" + this.stateName + "'";
		if (this.categoryName != null)
			filter += "category='" + this.categoryName + "'";
		if (this.modelId != null)
			filter += "modelId='" + this.modelId + "'";
		if (this.filterKeys.size() > 0)
			filter += this.filterKeys.toString();
		filter += "]";
		return filter;
	}
	
}