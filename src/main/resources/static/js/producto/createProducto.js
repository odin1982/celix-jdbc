$(document).ready(function() {
	document.getElementById('precioCompra').disabled = false;
	document.getElementById('precioCompraIVA').disabled = true;
//jquery validation toma el atributo name, con thymeleaf en los select se toma th:field	
$.validator.addMethod("validaSelect", function(value, element, arg){
  return arg != value;
 }, "Seleccione una opción");

	$("#createProducto").validate({
		rules: {
			nombre: "required",
			descripcion: "required",
			idTipoProducto: {validaSelect:"0"},
			idMarca: {validaSelect:"0"},
		},
		messages: {
			nombre: "Campo obligatorio",
			descripcion: "Campo obligatorio",
			idTipoProducto: "Escoge una opción",
			idMarca: "Escoge una opción",
		},
		errorElement: "em",
				errorPlacement: function ( error, element ) {
					// Add the `invalid-feedback` class to the error element
					error.addClass( "invalid-feedback" );

					if ( element.prop( "type" ) === "checkbox" ) {
						error.insertAfter( element.next( "label" ) );
					} else {
						error.insertAfter( element );
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					$( element ).addClass( "is-invalid" ).removeClass( "is-valid" );
				},
				unhighlight: function (element, errorClass, validClass) {
					$( element ).addClass( "is-valid" ).removeClass( "is-invalid" );
				}
	});
	
//	$.validator.addMethod("selectcheck", function (value,element) {
//	console.log("value:" + value);
//	console.log("element:" + element);
//        return (value != '0');
//    }, "year required");

});

	

function changeStatusPrecioCompraConIVA(){
		 if (document.getElementById('precioCompraConIVA').checked) { 
			 document.getElementById('precioCompra').disabled = true;
			 document.getElementById('precioCompra').value = "";
			 document.getElementById('precioCompraIVA').disabled = false;
		 } 
		 else {
			 document.getElementById('precioCompra').disabled = false;
			 document.getElementById('precioCompraIVA').disabled = true;
			 document.getElementById('precioCompraIVA').value = "";
		 }
}