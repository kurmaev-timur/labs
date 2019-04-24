package com.con1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nag.config.NagContextPlugin;
import com.tibbo.aggregate.common.context.CallerController;
import com.tibbo.aggregate.common.context.Context;
import com.tibbo.aggregate.common.context.ContextException;
import com.tibbo.aggregate.common.context.UncheckedCallerController;
import com.tibbo.aggregate.common.datatable.*;
import com.tibbo.linkserver.Server;
import com.tibbo.aggregate.common.context.*;
import com.tibbo.aggregate.common.server.*;
import com.tibbo.linkserver.*;
import com.tibbo.linkserver.context.*;


public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
	}
	  
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String reply = "";
		if (cmd.equals("updateTemp")) {
			String temp = "";
			//String temp = "[[0, 0, 60], [1, 10, 60], [2, 23, 60], [3, 17, 60], [4, 18, 60], [5, 9, 60], [6, 11, 60], [7, 27, 60], [8, 33, 60], [9, 40, 60], [10, 32, 60], [11, 35, 60], [12, 30, 60], [13, 40, 60], [14, 42, 60], [15, 47, 60], [16, 44, 60], [17, 48, 60], [18, 52, 60], [19, 54, 60], [20, 42, 60], [21, 55, 60], [22, 56, 60], [23, 57, 60], [24, 60, 60], [25, 50, 60], [26, 52, 60], [27, 51, 60], [28, 49, 60], [29, 53, 60], [30, 55, 60], [31, 60, 60], [32, 61, 60], [33, 59, 60], [34, 62, 60], [35, 65, 60], [36, 62, 60], [37, 58, 60], [38, 55, 60], [39, 61, 60], [40, 64, 60], [41, 65, 60], [42, 63, 60], [43, 66, 60], [44, 67, 60], [45, 69, 60], [46, 69, 60], [47, 70, 60], [48, 72, 60], [49, 68, 60], [50, 66, 60], [51, 65, 60], [52, 67, 60], [53, 70, 60], [54, 71, 60], [55, 72, 60], [56, 73, 60], [57, 75, 60], [58, 70, 60], [59, 68, 60]]";
			try {
				CallerController uncheckedCallerController = new UncheckedCallerController();
				Context nag = Server.getContextManager().get("nag.test1", uncheckedCallerController);
				temp = nag.getVariable("temp_array_table").clone().rec().getString("temp_array");
			} catch (ContextException ex) {
				NagContextPlugin.LOGGER.warn(ex.getMessage());
			}
			reply = temp;
		}
		if (cmd.equals("updateHum")) {
		    String hum = "";
			//String hum = "[[0, 0], [1, 5], [2, 15], [3, 17], [4, 18], [5, 9], [6, 11], [7, 27], [8, 33], [9, 40], [10, 32], [11, 35], [12, 30], [13, 40], [14, 42], [15, 47], [16, 44], [17, 48], [18, 52], [19, 54], [20, 42], [21, 55], [22, 56], [23, 57], [24, 60], [25, 50], [26, 52], [27, 51], [28, 49], [29, 53], [30, 55], [31, 60], [32, 61], [33, 59], [34, 62], [35, 65], [36, 62], [37, 58], [38, 55], [39, 61], [40, 64], [41, 65], [42, 63], [43, 66], [44, 67], [45, 69], [46, 69], [47, 70], [48, 72], [49, 68], [50, 66], [51, 65], [52, 67], [53, 70], [54, 71], [55, 72], [56, 73], [57, 75], [58, 70], [59, 68]]";
			try {
				CallerController uncheckedCallerController = new UncheckedCallerController();
				Context nag = Server.getContextManager().get("nag.test1", uncheckedCallerController);
				hum = nag.getVariable("hum_array_table").clone().rec().getString("hum_array");
			} catch (ContextException ex) {
				NagContextPlugin.LOGGER.warn(ex.getMessage());
			}
			reply = hum;
		}
		if (cmd.equals("updateLog")) {
		    String log = "";
		    //String log = "[[\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"вык\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"], [\"январь\", \"реле\", \"выкл\"]]";
			try {
				CallerController uncheckedCallerController = new UncheckedCallerController();
				Context nag = Server.getContextManager().get("nag.test1", uncheckedCallerController);
				log = nag.getVariable("log_array_table").clone().rec().getString("log_attay");
			} catch (ContextException ex) {
				NagContextPlugin.LOGGER.warn(ex.getMessage());
			}
			reply = log;
		}
		if (cmd.equals("setTemp")) {
			try {
				Integer critTemp = Integer.parseInt(request.getParameter("critTemp"));
				CallerController uncheckedCallerController = new UncheckedCallerController();
				Context nag = Server.getContextManager().get("nag.test1", uncheckedCallerController);
				DataTable dtTemp = new SimpleDataTable(nag.getVariable("critical_table", uncheckedCallerController).getFormat());
				dtTemp.addRecord(critTemp);
				nag.setVariable("critical_table", dtTemp);
				reply = "ok";
			} catch (ContextException ex) {
				reply = "ko";
				NagContextPlugin.LOGGER.warn(ex.getMessage());
			}
		}
		if (cmd.equals("onR")) {
			try {
				CallerController uncheckedCallerController = new UncheckedCallerController();
				Context nag = Server.getContextManager().get("nag.test1", uncheckedCallerController);
				DataTable rl = new SimpleDataTable(nag.getVariable("relay_state_table", uncheckedCallerController).getFormat());
				rl.addRecord("0");
				nag.setVariable("relay_state_table", rl);
				reply = "ok";
			} catch (ContextException ex) {
				reply = "ko";
				NagContextPlugin.LOGGER.warn(ex.getMessage());
			}
		}
		if (cmd.equals("offR")) {
			try {
				CallerController uncheckedCallerController = new UncheckedCallerController();
				Context nag = Server.getContextManager().get("nag.test1", uncheckedCallerController);
				DataTable rl = new SimpleDataTable(nag.getVariable("relay_state_table", uncheckedCallerController).getFormat());
				rl.addRecord("1");
				nag.setVariable("relay_state_table", rl);
				reply = "ok";
			} catch (ContextException ex) {
				reply = "ko";
				NagContextPlugin.LOGGER.warn(ex.getMessage());
			}
		}
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(reply);
		out.flush();
		out.close();
	}
	  
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	  
	@Override
	public void destroy() {
	}
}
