$(document).ready(function() {
	var txtCorreoElectronico	= document.getElementById("correoElectronico");
	
	$("#createProveedor").validate({
		rules: {
			nombre: "required",
			direccion: "required",
			telefono:{
      			required: true,
      			digits: true
    		},
    		rfc: "required",
			razonSocial: "required",
			correoElectronico:{
				email:{
					depends:isCorreoElectronicoEmpty
				}
			}
		},
		messages: {
			nombre: "Campo obligatorio",
			direccion: "Campo obligatorio",
			telefono:{
      			required: "Campo obligatorio",
      			digits: "Solo se permiten números"
    		},
    		rfc: "Campo obligatorio",
			razonSocial: "Campo obligatorio",
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
	
	function isCorreoElectronicoEmpty() {
    	return txtCorreoElectronico.value.length > 0;
	}
});