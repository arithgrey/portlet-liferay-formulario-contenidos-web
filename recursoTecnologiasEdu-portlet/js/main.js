		var campos = 0;
		function agregarCampo(){
			  campos = campos + 1;
			  var NvoCampo= document.createElement("div");
			  NvoCampo.id= "divcampo_"+(campos);
			  NvoCampo.innerHTML=
			     "<table>" +
			     "   <tr>" +
			     "     <td nowrap='nowrap'>" +
			     "        <input type='text' placeholder='Etiqueta descriptiva' size='20' name='tag_" + campos +
			                   "' id='tag_" + campos + "'required >" +
			     "     </td>" +
			     "     <td nowrap='nowrap'>" +
			     "        <a href='JavaScript:quitarCampo("+campos+");'><img src='http://tecnologias.itesmrzmcm.dev.factor.mx/ie-rzmcm-theme/images/common/close.png' width='18px'></a>" +
			     "     </td>" +
			     "   </tr>" +
			     "</table>";
			   var contenedor= document.getElementById("contenedorcampos");
			   contenedor.appendChild(NvoCampo);	
		}
			
		
			

				function quitarCampo(iddiv){
					  var eliminar = document.getElementById("divcampo_" + iddiv);
					  var contenedor= document.getElementById("contenedorcampos");
					  contenedor.removeChild(eliminar);
				}
				

				

				function getNivel(v){
					
					
					
					var pro = document.getElementById("areeaprofesional");
					var pre = document.getElementById("areeaprepa");
					
					if(v.value=="Preparatoria"){

						pre.style.display = "block";
						pro.style.display = "none";
						
					}else if(v.value=="Profesional"){
						pre.style.display = "none";
						pro.style.display = "block";
					}else{
						
						pro.style.display = "block";
						pre.style.display = "block";

						
					}
				}
										
				function marcar(source)
			    {
			        checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
			        for(i=0;i<checkboxes.length;i++) //recoremos todos los controles
			        {
			            if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
			            {
			            	checkboxes[i].checked=source.checked;
			            }
			        }
			    }
				
				
				
				
				
				
				function marcarprepa(source){

		    		checkboxes=document.getElementById('areeaprepa').getElementsByTagName('input');
		    		for(i=0;i<checkboxes.length;i++) //recoremos todos los controles
		        	
		        	{
		            if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
		            {
		            	checkboxes[i].checked=source.checked;
		            }
		        }
		    }
				
				
		    function marcarprofesional(source){
		    		checkboxesp=document.getElementById('areeaprofesional').getElementsByTagName('input');
		    		for(i=0;i<checkboxesp.length;i++){

		            if(checkboxesp[i].type == "checkbox"){
		            	checkboxesp[i].checked=source.checked;
		            }
		        }
		    }

		   function oculandmost(v){

		   		var campo=document.getElementById("urlcampoimg");
		   		var upload=document.getElementById("urlupimg");
			   	alert(v.value);		
			   	
			   	if (v.value == "URL") {

			   			campo.style.display="block";
			   			upload.style.display="none";

			   	}else{

			   			
			   			upload.style.display="block";
			   			campo.style.display="none";
			   	}			   
		   }