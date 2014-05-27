<%@page import="com.liferay.portal.model.User"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portlet.journal.model.JournalArticleConstants"%>
<%@page import="com.liferay.portlet.asset.model.AssetCategory"%>
<%@page import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.model.Company"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%
%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
	<portlet:defineObjects />

	<%
		String Type=renderRequest.getParameter("Type");
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Company company=themeDisplay.getCompany();
		long companyId= company.getCompanyId();		
		/*groupId*/
		
		
		long userId=themeDisplay.getUserId();
		String username = PortalUtil.getUserName(userId, null);
		long groupId=themeDisplay.getLayout().getGroupId();
		String languageId = LanguageUtil.getLanguageId(request);
		double  version=JournalArticleConstants.VERSION_DEFAULT;
		String portletId = themeDisplay.getPortletDisplay().getId();
		User user=themeDisplay.getUser();
		String UserUuid=user.getUserUuid();
		
		
	%>	

	
	<portlet:actionURL var="urlNext" name="procesarInfo">
		<portlet:param name="jspPage" value="/view.jsp"/>
	</portlet:actionURL>
	
	<div id="demoWrapper" >
	
	<div class="tecnologia_recursos_form" id="tecnologia_recursos_form">
	
	<form action=<%=urlNext%> method="GET" id="form_dinamic" >

		<div id="fieldWrapper">

			<span id="finland" class="step">
			<span class="font_normal_07em_black">Paso uno - Datos del recurso</span><br/>



<%---------------------------Recurso----------------------------%>
	<section class='datos_recurso' id='datos_recurso'>	
		<table>
		<tr>
			<td><div>Nombre del Recurso:</div></td>
			
			<td>
				<div><input  id="titulo" name="titulo"  type="text" 
			 			 placeholder='Titulo descriptivo del recurso' required/>
			 	</div>
			 </td>
		 </tr>

		<tr>

			<td><label>Descripción: </label></td>
			<td><textarea name="descripcion" type="text"  placeholder="Breve descripción"/></textarea></td>
		</tr>	 	


	<%--------------Tipo de recurso----------%>	

	<tr>
		<td><label>Tipo del Recurso:</label></td>
		
		<td>
		    <select name="tipodelRecurso" id="tipodelRecurso"  class="tipodelRecurso">
					<%	
					OrderByComparator orderBytipoRecurso = null;
					List<AssetCategory> categoriasTipoRecurso
						=AssetCategoryLocalServiceUtil.getVocabularyCategories(46364959,0,
								AssetCategoryLocalServiceUtil.getVocabularyCategoriesCount(46364959),orderBytipoRecurso);
					
					for(int rr=0; rr<=categoriasTipoRecurso.size()-1; rr++){
						
							%><option value="<%=categoriasTipoRecurso.get(rr).getCategoryId()%>">
							<%=categoriasTipoRecurso.get(rr).getName() %> </option><% 
					}
					%>
			</select>
				    
	    </td>	
	  </tr>  	
	


	<%------------------------------Tema recurso -----------------------------------------------------%>
	<tr>
		<td>
		<label>Tema del Recurso: </label>
		</td>
		<td><input  name="temarecurso" type="text"  placeholder='Tema relacionado'/></td>
	</tr>	
					
</section>


<%------------------------------Nivel--------------------------------------%>
	
	
			<tr>
				<td>
					<label>Nivel / Área:</label>
				</td>

				<td>
							<%
							
							OrderByComparator orderByprepa = null;
							List<AssetCategory> categoriasPrepa
								=AssetCategoryLocalServiceUtil.getVocabularyCategories(46373708,0,
										AssetCategoryLocalServiceUtil.getVocabularyCategoriesCount(46373708),orderByprepa);
								
							for(int vbf=0; vbf<=categoriasPrepa.size()-1; vbf++){
								
								if (categoriasPrepa.get(vbf).isRootCategory()){
									
									
									%><%=categoriasPrepa.get(vbf).getName()%> : <input type="checkbox" name="nivel_<%=vbf%>"   
									id="nivel_<%=vbf%>"  value="<%=categoriasPrepa.get(vbf).getName()%>" onclick="getNivel(this)" /><br><% 						
										
								}		 
							}		
							%>											
				</select>
				
				
				
				
				
				
				

			
<%--------------------------------Area Prepa ------------------------------------------%>

	<div id="areeaprepa"  class="areeaprepa">
				<h1 id="areeaprepalab" id="areeaprepalab" class="areeaprepalab" >Area prepa</h1>
		<p id="marcarprepa"  class="marcarprepa">Marcar/Desmarcar Todos <input type="checkbox" onclick="marcarprepa(this)"/></p>	

				<%
				
				int posactual=0;
				long ChildCategoriesPrepaProfesional=46373710;
					List<AssetCategory> FEEE =AssetCategoryLocalServiceUtil.getChildCategories
							(ChildCategoriesPrepaProfesional);
					
					for(int kkk=0; kkk<=FEEE.size()-1;  kkk++){
						
						posactual++;
						%><input id='segundonivel' type="checkbox" name="cat_<%=posactual%>"
						value="<%=FEEE.get(kkk).getCategoryId()%>"  ><%=FEEE.get(kkk).getName()%><br><%
						long parent=FEEE.get(kkk).getCategoryId();
						
						List<AssetCategory> chhhjos=AssetCategoryLocalServiceUtil.getChildCategories(parent);
						for(int v1=0; v1<=chhhjos.size()-1; v1++){
							
							posactual++;
							%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id='tercernivel' name="cat_<%=posactual%>" 
							value="<%=chhhjos.get(v1).getCategoryId()%>"  ><%=chhhjos.get(v1).getName()%><br><%
						}
					}
				%>
</div>
<%-----------------Area profesional -----------------------------------------------%>	
<div id="areeaprofesional"  class="areeaprofesional" >

					<h1 id="areeaprofesionalab" class="areeaprofesionalab" >Area profesional</h1>
					<p>Marcar/Desmarcar Todos <input type="checkbox" onclick="marcarprofesional(this)"/></p> 	
					<%	
							int nexteleme=0;
							List<AssetCategory> FEEEx =AssetCategoryLocalServiceUtil.getChildCategories(46373711);		
							for(int kkk=0; kkk<=FEEEx.size()-1;  kkk++){
								
								nexteleme++;
								%><input type="checkbox" id="catprofe" name="catprofesional_<%=nexteleme%>" 
										value="<%=FEEEx.get(kkk).getCategoryId()%>"  ><%=FEEEx.get(kkk).getName()%><br><%
										
										long parent=FEEEx.get(kkk).getCategoryId();
										List<AssetCategory> hijosProfesional=AssetCategoryLocalServiceUtil.getChildCategories(parent);
										
										for(int v1=0; v1<=hijosProfesional.size()-1; v1++){						
											nexteleme++;
											%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input id="areaprehijo" type="checkbox" name="catprofesional_<%=nexteleme%>" 
											value="<%=hijosProfesional.get(v1).getCategoryId()%>">
											<%=hijosProfesional.get(v1).getName()%><br><%
										}
								}	
					%>
	</div>				
				
				</td>
			</tr>
			
			
			
			
			
			
			
			
			
			
			
		
<%-----------------------------------------plataforma ----------------------------------------------%>
	
	<tr>
	<td><label>Plataforma:</label></td>
	<td><select multiple name="plataformaa" class="plataformaa" id="plataformaa" >	
	<option>No definida </option>
	
<%
	OrderByComparator orderByplataforma = null;
	List<AssetCategory> categoriasplataformaa
		=AssetCategoryLocalServiceUtil.getVocabularyCategories(46373789,0,
				AssetCategoryLocalServiceUtil.getVocabularyCategoriesCount(46373789),orderByplataforma);
		for(int zzt=0;  zzt<=categoriasplataformaa.size()-1; zzt++){
			
			%><option value="<%=categoriasplataformaa.get(zzt).getCategoryId()%>">
			<%=categoriasplataformaa.get(zzt).getName() %></option><% 
		}
%>	
	<option>Agregar</option>	
	<option>Todas</option>

	</select>
	</td>
	</tr>
<%-----------------------------------------Dipositivo----------------------------------- --%>		
	<tr>
		<td><label>Dispositivo:</label></td>
		<td><select multiple name="dispositivodeacceso" class="dispositivodeacceso" id="dispositivodeacceso">

		<option>No definido</option>	

	<%
OrderByComparator orderByComparator2 = null;
List<AssetCategory> categoriasdipositivos
	=AssetCategoryLocalServiceUtil.getVocabularyCategories(46373796,0,
			AssetCategoryLocalServiceUtil.getVocabularyCategoriesCount(46373796),orderByComparator2);
	
	for(int xxh=0; xxh<=categoriasdipositivos.size()-1;  xxh++){
		
		%><option value="<%=categoriasdipositivos.get(xxh).getCategoryId()%>">
			<%=categoriasdipositivos.get(xxh).getName()%></option><% 
	}	
	%>	
		<option>Agregar</option>
		<option>Todas</option>

	</select></td>
	</tr>
<%--------------------------------Licenciado del recurso----------------------------------- --%>
	<tr>
		<td><label>Licenciado del  Recurso:</label></td>
		<td><select name="licenciaRecurso" class="licenciaRecurso" id="licenciaRecurso" >
				  <option value="ITESM">ITESM</option>
				  <option value="Libre">Libre</option>
			</select>
		</td>	
	</tr>
	
<%------------Institucion, colaboracion, premio urlYoutube----------------------------------- --%>

	<tr>
		<td><label>Institucion de <br> participación externa:</label></td>
			<td><input  id='institucionRecurso' name="institucionRecurso" type="text"  
			placeholder='En caso de ser externo'/></td>
	</tr>	
	<tr>
		<td><label>Autores externos:</label></td>
		<td><input id='colaboracionRecurso' name="colaboracionRecurso" type="text"  
	placeholder='En colaboración' /></td>
	</tr>
	<tr>
		<td><label>Distinciones:</label></td>
		<td><input  id='premioRecurso' name="premioRecurso" type="text"  
		placeholder='Premio adquirido'/></td>
	</tr>

	<tr>
		<td><label>Youtube url:</label></td>
		<td><input id='youtubeURL' name="youtubeURL" type="text" 
		placeholder='vídeo demostrativo' /></td>
	</tr>
	<tr>

		<td>
			<label>Imagen Pequeña</label>
			   
		</td>			
		<td>				
												
				<input type="checkbox" name="Direcciondeimagen" id="Direcciondeimagen"/> <label>Dirección Imagen</label>
				<input type="checkbox" name="urlimagenup" id="urlimagenup"/> <label>Subir Imagen </label>
				
		</td>			
	</tr>
	
				<tr>
				<td><label>Url de imagen</label></td>
				<td><input type="text" name="urlimagen" placeholder="url de imagen" /></td>
				</tr>
	
	
	
			<tr>
				<td>
						<p>Imagen desde computadora:</p>
				</td>
				<td>
					<input type="file" name="fileName" size="75">
				</td>
			</tr>

	

	</table>

	</section>

</span>


						<span class="step" id="first">
			<span class="font_normal_07em_black">Paso dos - Datos del publicador</span><br/>


	<%-----------------Datos del autor----------------%>

	<section class='datos_autor' id='datos_autor'>
		<table style="width='100%">
		<tr>
			<td><div>Nombre del autor: </div></td>
		 	<td><div><input id="nombreautor" value="<%= username %>" name="nombreautor" type="text"  SIZE="20"
			placeholder='Nombre del redactor'  required /></div></td>
		</tr>
		<tr>
			<td> Roll del publicador:</td>
			 <td><select name="rollrecurso" id="rollrecurso" class="rollrecurso">			 
				  <option value="Colaborador">Colaborador</option>
				  <option value="Responsable">Responsable </option>
				  <option value="Documentador"> Documentador</option>
				  
			</select>
			</td> 
		</tr>		
<%----------------------Campus del publicador  ---------------------------- --%>
	
		<tr>
			<td>Campus del publicador:</td>
			<td>
		<%
		OrderByComparator orderByComparator = null;
		List<AssetCategory> categoriasPrimer
			=AssetCategoryLocalServiceUtil.getVocabularyCategories(46364960,0,
					AssetCategoryLocalServiceUtil.getVocabularyCategoriesCount(46364960),orderByComparator);
		%>	
			<select name="campusRecurso" >
					
				<%
				  for(int camRecurso=0; camRecurso<=categoriasPrimer.size()-1; camRecurso++){
					  %><option value="<%=categoriasPrimer.get(camRecurso).getName() %>" ><%=categoriasPrimer.get(camRecurso).getName()%> </option><%   	
				  }
				%>	
			</select>
		</td>
	</tr>
</table>
</section>
</span>


		<%-----------------------------------------------Tag ----------------------------------------------%>
<span id="finland2" class="step">
<span class="font_normal_07em_black">Tercer paso - Etiquetas relacionadas</span><br/>


			<h3 onclick="JavaScript:agregarCampo()" id="mastag" class="mastag" >
					Agregar tag 
			<img src='http://tecnologias.itesmrzmcm.dev.factor.mx/ie-rzmcm-theme/images/common/add.png' width='15px'></h3>	
			 
			 <div id="contenedorcampos">
			   	<input type="text" name="tag_0" id="tag_0"  placeholder="Palabra clave">
			 </div>	    
			 
			 
			 <%----Datos ocultos desde lifeeray -------%>
			 <input type="hidden" name="companyId" value="<%=companyId%>">
			 <input type="hidden" name="groupId" value="<%=groupId%>">
			 <input type="hidden" name="username" value="<%=username%>">
			 <input type="hidden" name="userId" value="<%=userId%>">
			 <input type="hidden" name="Type" value="<%=Type%>">
			 <input type="hidden" name="languageId" value="<%=languageId%>">
			 <input type="hidden" name="version" value="<%=version%>">
			 <input type="hidden" name="portletId" value="<%=portletId%>">
			 <input type="hidden" name="UserUuid" value="<%=UserUuid%>">
			 
			 
			
								
			</span>			   			   

			  	<div id="demoNavigation" style="margin-top: 2em;">							
							<input class="navigation_button" id="Regresar" value="Regresar" type="reset" />
							<input class="navigation_button" id="Siguiente" value="Siguiente" type="submit" />
									
				</div>								

				</form>

			</div>		
		</div>

	<script type="text/javascript">
		$(function(){
				$("#form_dinamic").formwizard({ 
					formPluginEnabled: true,
					validationEnabled: true,
					focusFirstInput : true,
					
					}
				);
			});
	</script>
	