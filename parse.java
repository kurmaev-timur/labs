/* От устройства приходят события с разными топиками, нужно распарсить входные события и заполнять таблицу.
в таблице нужно хранить последние 60 событий */

import com.tibbo.aggregate.common.context.*;
import com.tibbo.aggregate.common.datatable.*;
import com.tibbo.aggregate.common.server.*;
import com.tibbo.linkserver.*;
import com.tibbo.linkserver.context.*;     
import java.util.Date;

public class %ScriptClassNamePattern% implements FunctionImplementation
{
  public DataTable execute(Context con, FunctionDefinition def, CallerController caller, RequestController request, DataTable parameters) throws ContextException
  {       

   //Инициализируем переменные                                                            
   DataTable packet = new SimpleDataTable();
   DataRecord packetRec = null; 
   DataRecord dtPacketRec = null; 
   String topic = "", message = "" parseTopic = "";  
 
   TableFormat tfinput = new TableFormat();
   tfinput.addField('S', "topic", "topic", "", true);   
   tfinput.addField('T', "message", "message",  null, true);   
   DataTable dtInput = new SimpleDataTable(tfinput);
   DataRecord rec_message = null;

   Date time = new Date();
   TableFormat tfResult_temp = new TableFormat();
   tfResult_temp.addField('D', "time", "Время собыбтия", null, true);   
   tfResult_temp.addField('F', "temp", "Температура, C",  null, true);   
   DataTable dtResult_temp = new SimpleDataTable(tfResult_temp);
 
   TableFormat tfResult_hum = new TableFormat();
   tfResult_hum.addField('D', "time", "Время собыбтия",  null, true); 
   tfResult_hum.addField('F', "hum", "Влажность, %",  null, true);  
   DataTable dtResult_hum = new SimpleDataTable(tfResult_hum); 
        
   DataTable dtSource_temp = new SimpleDataTable();
   DataTable dtSource_hum = new SimpleDataTable();
   DataTable dtSource_crit = new SimpleDataTable();
   DataTable dtSource_log = new SimpleDataTable();

   //Из контекстов берём уже созданные таблицы.
   dtSource_crit = con.getContextManager().get("con1.test1", caller).getVariable("critical_table", caller).clone();  
   dtSource_log = con.getContextManager().get("con1.test1", caller).getVariable("log_table", caller).clone();  
   dtSource_temp = con.getContextManager().get("con1.test1", caller).getVariable("source_table_temp", caller).clone();
   dtSource_hum = con.getContextManager().get("con1.test1", caller).getVariable("source_table_hum", caller).clone();
   
   //берем параметры, которые подаются на вход выполняемой программы
   packetRec = parameters.rec();
   dtInput = packetRec.getDataTable("dataTable");  
   dtPacketRec = dtInput.rec();                                           
   packet = dtPacketRec.getDataTable("message");
   topic = dtPacketRec.getString("topic");
   //парсим топик, чтобы разграничить входные данные на температуру и влажность
   parseTopic = topic.substring(22, 23);
   recMessage = packet.rec();
   message = recMessage.getString("textMessage"); 
   Float floatMessage = new Float(message);
   Integer recNum = 0;
        
   DataRecord critRec = dtSource_crit.rec();
   Float fCritRec =  critRec.getFloat("critical_temp"); 
  
  //для заполнения нужных таблиц проверяем какой у нас топик
   if(parseTopic.equals("h")){
       dtSource_hum.addRecord(time, floatMessage);
       recNum =  dtSource_hum.getRecordCount();
       //в таблице не должно быть больше 60 записей
        if (recNum > 60 ){     
          dtSource_hum.sort("time", true);
            for (Integer i1 = recNum-60; i1 > 0; i1--)
            {
               dtSource_hum.removeRecord(i1-1);                    
            }
         }
       con.setVariable("source_table_hum", dtSource_hum);
   }
 
  if(parseTopic.equals("t")){
     if (floatMessage > fCritRec){
      dtSource_log.addRecord(time, "Температура Крит. превышена", floatMessage);
      con.setVariable("log_table", dtSource_log);
    }
  
     dtSource_temp.addRecord(time, floatMessage);
     recNum =  dtSource_temp.getRecordCount();
     if (recNum > 60 ){     
      dtSource_temp.sort("time", true);
         for (Integer i1 = recNum-60; i1 > 0; i1--){
            dtSource_temp.removeRecord(i1-1);                    
         }
      }
        con.setVariable("source_table_temp", dtSource_temp);
   }         
      return null;
  }
}
