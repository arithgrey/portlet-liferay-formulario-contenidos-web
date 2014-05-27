package com.mx.factorevolucion.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.mx.factorevolucion.model.updateJournalArticle;
public class formulario extends MVCPortlet{
	
	private updateJournalArticle objJournal = new updateJournalArticle();
	public void procesarInfo(ActionRequest actionRequest, ActionResponse actionResponse)
	
			throws IOException, PortletException{		
			
			String companyId=actionRequest.getParameter("companyId");
			String username=actionRequest.getParameter("username");
			String userId=actionRequest.getParameter("userId");
			String  groupId = actionRequest.getParameter("groupId");
			String Type=actionRequest.getParameter("Type");
			String languageId=actionRequest.getParameter("languageId");
			String version = actionRequest.getParameter("version");
			String portletId=actionRequest.getParameter("portletId");
			String UserUuid=actionRequest.getParameter("UserUuid");			
			String nombreautor = actionRequest.getParameter("nombreautor");
			String rollrecurso = actionRequest.getParameter("rollrecurso");
			String campusRecurso = actionRequest.getParameter("campusRecurso");
			String titulo = actionRequest.getParameter("titulo");
			String descripcion= actionRequest.getParameter("descripcion");
			String tipodelRecurso = actionRequest.getParameter("tipodelRecurso");
			String temarecurso= actionRequest.getParameter("temarecurso");			
			String licenciaRecurso=actionRequest.getParameter("licenciaRecurso");
			String institucionRecurso=actionRequest.getParameter("institucionRecurso");
			String colaboracionRecurso=actionRequest.getParameter("colaboracionRecurso");
			String premioRecurso=actionRequest.getParameter("premioRecurso");
			String youtubeURL=actionRequest.getParameter("youtubeURL");
			String urlimagen= actionRequest.getParameter("urlimagen");								
						
			
			int x=0;			
			
			if(titulo!=""){
								
				objJournal.createJournalArticle(companyId, username, userId, Type,
						languageId, titulo, descripcion, groupId, version, urlimagen,
						portletId, UserUuid, rollrecurso, campusRecurso, nombreautor, temarecurso, 
					licenciaRecurso, institucionRecurso, colaboracionRecurso, premioRecurso, youtubeURL);
				
				
				long resullJournalArticle=objJournal.getEntryIddb();
				
				if(resullJournalArticle !=0 ){
					
					
			/*-------------------------categorias prepa---------------------------*/
					
				int xxk=0;
				
				while(xxk<=500){
					
					String cat_pos="cat_"+xxk;
					String cat_pk=actionRequest.getParameter(cat_pos);
					
					if(cat_pk!=null){
						
						try{
							AssetEntryUtil.addAssetCategory(resullJournalArticle, GetterUtil.getLong(cat_pk));					
						
						}catch(Exception d){
							
						}
						
					}
					xxk++;
				}	
				
			/*-------------------------categorias profesional---------------------------*/
				
				
				int xxkx=0;			
				while(xxkx<=1000){
					
					String cat_pos="catprofesional_"+xxkx;
					String cat_pk_profesional=actionRequest.getParameter(cat_pos);
				
					if(cat_pk_profesional!=null){
						
						try{							
							AssetEntryUtil.addAssetCategory(resullJournalArticle, GetterUtil.getLong(cat_pk_profesional));
							
						}catch(Exception d){
							
						}						
					}
					xxkx++;
				}
	/***************************Valores en plataforma ***************************/
				

				String[] items = actionRequest.getParameterValues("plataformaa");
				
				if(items==null){}else{
					
					int yhg=0;
					while(yhg<=items.length-1){
						
						if(items[yhg].length()>0){
							
							try{
							AssetEntryUtil.addAssetCategory(resullJournalArticle,
									GetterUtil.getLong(items[yhg]));
							}catch(Exception df){
								
							}						
							
						}else{						
						}						
						yhg++;					
					}		
				}
				
				
	/***************************Valores en Dispositivo ***************************/
				
				
				String[] itemsD = actionRequest.getParameterValues("dispositivodeacceso");			

				if(itemsD==null){				
				}else{				
					int yhk=0;
					while(yhk<=itemsD.length-1){
						
						if(itemsD[yhk].length()>0){
							
							try{
								AssetEntryUtil.addAssetCategory(resullJournalArticle,
										GetterUtil.getLong(itemsD[yhk]));							
								}catch(Exception df){
									
								}													
						}else{}					
						yhk++;	
					}
				}
				
				
	/***************************Recogemos tags*****************************/								
				
				String arrTags[] = new String [10];			
				int tagCount=0;
				
				while(tagCount<=10){
					
					String tagActual=actionRequest.getParameter("tag_"+tagCount);				
					
					if(tagActual!=null){
						arrTags[tagCount]=tagActual;
						System.out.println(tagActual);
						
					}							
					tagCount++;				
				}								
				
								
				try{
					
					objJournal.asociarTags(arrTags, companyId, groupId, username, userId, UserUuid);
				
					
				}catch(Exception dd){
					
				}
				
				
				}else{
						System.out.print("Elementon cero::::!!!:::::::!:!::!");
				}
				
			}else{
				
				/*Titulo sin elementos*/
				System.out.print("El titulo se encuentra en blanco ingrese datos:!!");
				System.out.println(titulo);
				System.out.println("dddddd");
			}	
					
	}
	
}