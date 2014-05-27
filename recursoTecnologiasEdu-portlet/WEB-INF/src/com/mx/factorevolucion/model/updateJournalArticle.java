package com.mx.factorevolucion.model;

import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalContentSearch;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.service.JournalArticleResourceLocalServiceUtil;
import java.util.Date;
import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import java.util.List;
import com.liferay.portlet.asset.model.AssetTagStats;
import com.liferay.portlet.asset.service.AssetTagStatsLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portlet.journal.service.JournalContentSearchLocalServiceUtil;
public class updateJournalArticle {
	 
	JournalArticle articulo, journalArtinDb;
	JournalContentSearch jCSLSU, jCSLSUDataBase;	
	JournalArticleResource JAResources2, r;
	AssetEntry nuevoEntryDB;
	AssetTag assetTag, assetTagDB;	
	Date date=new  Date();
	Date exxp=new Date(114, 2, 23);
	Date c=new Date(112, 12, 5);
	long entryIddb=0;
	
	public updateJournalArticle(){}
	
	public long getEntryIddb(){
		return this.entryIddb;
	}
	
	public void createJournalArticle(String companyId, String username,String  userId, 
			String Type, String languageId, String titulo, String descripcion, String groupId, 
			String version, String urlimagen, String portletId, String UserUuid, String rollrecurso,
			String campusRecurso, String nombreautor, String temarecurso, String licenciaRecurso,
			String institucionRecurso, String colaboracionRecurso, String premioRecurso, 
			String youtubeURL){
		
		long companyILong=Long.parseLong(companyId);
		
		try{			
	
			
			String statusContenid="";		
			String content="<?xml version='1.0'?>"+
		"<root available-locales='en_US' default-locale='en_US'>"+
			"<dynamic-element instance-id='07NKNkni' name='autoresRecurso' type='text' index-type=''>"+
				"<dynamic-element instance-id='3kzxCYzf' name='rolRecurso' type='list' index-type='keyword'>"+
					"<dynamic-content><![CDATA["+rollrecurso+"]]></dynamic-content>"+
				"</dynamic-element>"+
				"<dynamic-element instance-id='0qurbXea' name='campusRecurso' type='list' index-type='keyword'>"+
					"<dynamic-content><![CDATA["+campusRecurso+"]]></dynamic-content>"+
				"</dynamic-element>"+
				"<dynamic-content><![CDATA["+nombreautor +"]]></dynamic-content>"+
			"</dynamic-element>"+
			"<dynamic-element instance-id='3lK65Oqd' name='temaRecurso' type='text' index-type='keyword'>"+
				"<dynamic-content><![CDATA["+ temarecurso+"]]></dynamic-content>"+
			"</dynamic-element>"+
			"<dynamic-element instance-id='OuyC9rD1' name='licenciaRecurso' type='list' index-type='keyword'>"+
				"<dynamic-content><![CDATA["+licenciaRecurso+"]]></dynamic-content>"+
			"</dynamic-element>"+
			"<dynamic-element instance-id='17IVhd90' name='institucionRecurso' type='text' index-type=''>"+
				"<dynamic-content><![CDATA["+institucionRecurso+"]]></dynamic-content>"+
			"</dynamic-element>"+
			"<dynamic-element instance-id='r3N5aa5i' name='colaboracionRecurso' type='text' index-type=''>"+
				"<dynamic-content><![CDATA["+colaboracionRecurso+ "]]></dynamic-content>"+
			"</dynamic-element>"+
			"<dynamic-element instance-id='nwZAs7Xu' name='premioRecurso' type='text_box' index-type='keyword'>"+
				"<dynamic-content><![CDATA["+premioRecurso +"]]></dynamic-content>"+
			"</dynamic-element>"+
			"<dynamic-element instance-id='9Rnh4ZyI' name='imagenesRecurso' type='image' index-type=''>"+
				"<dynamic-content><![CDATA[]]></dynamic-content>"+
			"</dynamic-element>"+
			"<dynamic-element instance-id='1PKAmXrg' name='youtubeURL' type='text' index-type=''>"+
				"<dynamic-element instance-id='TJ3eZZqU' name='video' type='document_library' index-type=''>"+
					"<dynamic-content><![CDATA[]]></dynamic-content>"+
				"</dynamic-element>"+
				"<dynamic-content><![CDATA["+youtubeURL+" ]]></dynamic-content>"+
			"</dynamic-element>"+
		"</root>";				

			
			ClassLoader cl2 = PortalClassLoaderUtil.getClassLoader();
			DynamicQuery dynamicJournal2=DynamicQueryFactoryUtil.forClass(JournalArticle.class, cl2);
			dynamicJournal2.setProjection(ProjectionFactoryUtil.property("articleId"));
			dynamicJournal2.add(PropertyFactoryUtil.forName("companyId").eq(companyILong));
			dynamicJournal2.addOrder(OrderFactoryUtil.desc("articleId"));
			dynamicJournal2.addOrder(OrderFactoryUtil.desc("createDate"));
			dynamicJournal2.setLimit(0, 2);						
			List listadoArticulos = JournalArticleLocalServiceUtil.dynamicQuery(dynamicJournal2);			
			int ultimoJournalArticle=GetterUtil.getInteger(listadoArticulos.get(0));
			
			int nuevoJ=ultimoJournalArticle+11;
			
			articulo=JournalArticleLocalServiceUtil.createJournalArticle(nuevoJ);
			articulo.setUserName(username);			
		 	articulo.setCompanyId(GetterUtil.getLong(companyId));
		 	articulo.setCreateDate(date);
		 	Date displaDd=articulo.getCreateDate();
		 	articulo.setDisplayDate(date); 
		 	articulo.setStatus(0);
		 	articulo.setStatusByUserId(GetterUtil.getLong(userId));
		 	articulo.setStatusByUserName(username);
		 	articulo.setIndexable(true);
		 	articulo.setType(Type);
		 	
		 	articulo.setDescriptionCurrentLanguageId(languageId);		 	
		 	articulo.setExpirationDate(exxp);
		 	articulo.setModifiedDate(displaDd);
		 	articulo.setStatusDate(displaDd);
		 	articulo.setTitle(titulo);
		 	articulo.setUserId(GetterUtil.getLong(userId));
		 	articulo.setDescription(descripcion);
		 	
		 	articulo.setContent(content);
		 	
		 	articulo.setGroupId(GetterUtil.getLong(groupId));		 			 
		 	articulo.setVersion(GetterUtil.getDouble(version));		 	
		 	articulo.setStructureId("46364370");		 	
		 	articulo.setTemplateId("46364375");		 			 	
		 	articulo.setSmallImageURL(urlimagen);		 	
		 	articulo.setSmallImage(true);
		 	
		 	
		 	long artID=GetterUtil.getInteger(ultimoJournalArticle+10);
		 	
		 	String articuloId=""+artID;
		 	articulo.setArticleId(articuloId);		 	
		 	long resourcePk = GetterUtil.getLong(artID)+2;
		 	articulo.setResourcePrimKey(resourcePk);
		 	journalArtinDb=JournalArticleLocalServiceUtil.addJournalArticle(articulo);		 	
		 	JAResources2=JournalArticleResourceLocalServiceUtil.createJournalArticleResource(resourcePk);
		 	JAResources2.setGroupId(journalArtinDb.getGroupId());
		 	JAResources2.setArticleId(journalArtinDb.getArticleId());
		 	
		 			 	
		 	r=JournalArticleResourceLocalServiceUtil.addJournalArticleResource(JAResources2);
		 	/*************************************************************/
		 	
		 	/**************************************************************/
			long contentSearchId=GetterUtil.getInteger(ultimoJournalArticle+19);
			
			jCSLSU=JournalContentSearchLocalServiceUtil.createJournalContentSearch(contentSearchId);
			jCSLSU.setGroupId(journalArtinDb.getGroupId());
			jCSLSU.setCompanyId(journalArtinDb.getCompanyId());
			jCSLSU.setArticleId(journalArtinDb.getArticleId());				
			jCSLSU.setPortletId(portletId);
			
			jCSLSU.setPrivateLayout(false);	
			jCSLSU.setLayoutId(4);
			jCSLSUDataBase=JournalContentSearchLocalServiceUtil.addJournalContentSearch(jCSLSU);				
			jCSLSUDataBase.setCachedModel(true);
			String classUuid=JAResources2.getUuid();
			DynamicQuery query = DynamicQueryFactoryUtil.forClass(AssetEntry.class);
			query.setProjection(ProjectionFactoryUtil.property("entryId"));
			query.addOrder(OrderFactoryUtil.desc("entryId"));
			query.setLimit(0, 2);
			
			List<AssetEntry> results=AssetEntryLocalServiceUtil.dynamicQuery(query);
			int ultimoEntry=GetterUtil.getInteger(results.get(0));
			int nuevoEntryId=ultimoEntry+17;
			
			AssetEntry nuevoEntry=AssetEntryLocalServiceUtil.
					createAssetEntry(GetterUtil.getInteger(ultimoJournalArticle+37));
			
			entryIddb = nuevoEntry.getEntryId();
			
			nuevoEntry.setVisible(true);
			
			nuevoEntry.setCompanyId(GetterUtil.getLong(companyId));
			
			nuevoEntry.setClassPK(r.getResourcePrimKey());
			nuevoEntry.setGroupId(GetterUtil.getLong(groupId));			
			nuevoEntry.setUserName(username);
			nuevoEntry.setUserId(GetterUtil.getLong(userId));
			nuevoEntry.setCreateDate(c);
			nuevoEntry.setModifiedDate(displaDd);
			nuevoEntry.setExpirationDate(exxp);
			nuevoEntry.setMimeType("text/html");
			nuevoEntry.setPublishDate(displaDd);
			nuevoEntry.setStartDate(displaDd);
			nuevoEntry.setEndDate(exxp);
			nuevoEntry.setCachedModel(true);
			nuevoEntry.setNew(true);
			nuevoEntry.setClassNameId(10108);
			nuevoEntry.setPriority(0);
			nuevoEntry.setTitle(titulo);
			nuevoEntry.setDescription(descripcion);
			nuevoEntry.setClassTypeId(46364371 );
			nuevoEntry.setUserUuid(UserUuid);
			
			nuevoEntry.setClassUuid(classUuid);
			nuevoEntry.setSummary(descripcion);
			nuevoEntry.setVisible(true);
			
			nuevoEntryDB=AssetEntryLocalServiceUtil.addAssetEntry(nuevoEntry);
			
			
		}catch(Exception o ){
			
		}		
	}
	
	public void asociarTags(String nameTags[], String companyId, String groupId, String username, 
			String  userId, String UserUuid){
		
		int z=0;
		while(z<=9){
			
			if(nameTags[z]==null){
				
			}else{
			
				
			try{
				/*Consultamos la existencia del tag*/
				ClassLoader clasLTagConsulta = PortalClassLoaderUtil.getClassLoader();
				DynamicQuery dynamicTagConsulta=DynamicQueryFactoryUtil.forClass(AssetTag.class, clasLTagConsulta)
						.add(PropertyFactoryUtil.forName("name").eq(nameTags[z]));
				
				List<AssetTag> listaTagConsulta=AssetTagLocalServiceUtil.dynamicQuery(dynamicTagConsulta);
			
					if(listaTagConsulta.size()==0){
						/*Creamos un nuevo tag*/
						ClassLoader clasLTag = PortalClassLoaderUtil.getClassLoader();
						DynamicQuery dynamicTag=DynamicQueryFactoryUtil.forClass(AssetTag.class, clasLTag);
						dynamicTag.addOrder(OrderFactoryUtil.desc("tagId"));
						dynamicTag.setLimit(0, 2);					
						List<AssetTag> listaTag=AssetTagLocalServiceUtil.dynamicQuery(dynamicTag);
						long nuevoTgId=0;
						long ultimoTgId=listaTag.get(0).getTagId();
						nuevoTgId=ultimoTgId+7;
						assetTag=AssetTagLocalServiceUtil.createAssetTag(nuevoTgId);
						assetTag.setCachedModel(true);
						
						assetTag.setCompanyId(GetterUtil.getLong(companyId));
						
						assetTag.setCreateDate(date);				
						assetTag.setGroupId(GetterUtil.getLong(groupId));
						assetTag.setModifiedDate(date);
						assetTag.setName(nameTags[z]);
						assetTag.setNew(true);
						assetTag.setUserId(GetterUtil.getLong(userId));
						assetTag.setUserName(username);
						assetTag.setUserUuid( UserUuid);
						assetTag.setAssetCount(1);
						assetTagDB=AssetTagLocalServiceUtil.addAssetTag(assetTag);								
						
						long idAssetTag=assetTagDB.getTagId()+2;				
						AssetTagStats assetTagStats=AssetTagStatsLocalServiceUtil.createAssetTagStats(idAssetTag);
						assetTagStats.setTagId(assetTagDB.getTagId());
						assetTagStats.setClassNameId(10108);
						assetTagStats.setAssetCount(1);
						AssetTagStatsLocalServiceUtil.addAssetTagStats(assetTagStats);				
						AssetEntryUtil.addAssetTag(nuevoEntryDB.getEntryId(), assetTagDB);
						AssetEntryPersistence persistence=AssetEntryUtil.getPersistence();
						persistence.addAssetTag(nuevoEntryDB.getEntryId(),assetTagDB );
						
					}else{					
						/*Relacionamos las etiquetas */				
						AssetEntryUtil.addAssetTag(nuevoEntryDB.getEntryId(), listaTagConsulta.get(0));					
					}
						}catch(Exception xxz){
							
					}
					
				
			}
				z++;
			
			}		
			
	}
	
	
}	
	
	
	

	
		
