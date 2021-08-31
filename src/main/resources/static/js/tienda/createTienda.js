$(document).ready(function() {
	var txtTelefonoFijo	= document.getElementById("telefonoFijo");
	var txtTelefonoCelular	= document.getElementById("telefonoCelular");
	var txtCorreoElectronico	= document.getElementById("correoElectronico");
	
	$("#createTienda").validate({
		rules: {
			nombre: "required",
			direccion: "required",
			telefonoFijo:{
				digits:{
					depends: isTelefonoFijoEmpty
				}
			},
			telefonoCelular:{
				digits:{
					depends: istelefonoCelularEmpty
				}
			},
			correoElectronico:{
				email:{
					depends:isCorreoElectronicoEmpty
				}
			}
		},
		messages: {
			nombre: "Campo obligatorio",
			direccion: "Campo obligatorio",
			correoElectronico: "Porfavor ingresa un correo electrónico valido"
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
	
	function isTelefonoFijoEmpty() {
    	return txtTelefonoFijo.value.length > 0;
	}
	
	function istelefonoCelularEmpty() {
    	return txtTelefonoCelular.value.length > 0;
	}
	function isCorreoElectronicoEmpty() {
    	return txtCorreoElectronico.value.length > 0;
	}
});