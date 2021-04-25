document.getElementById('precioCompra').disabled = false;
document.getElementById('precioCompraIVA').disabled = true;

function changeStatusPrecioCompraConIVA(){
	 if (document.getElementById('precioCompraConIVA').checked) { 
		 document.getElementById('precioCompra').disabled = true;
		 document.getElementById('precioCompraIVA').disabled = false;
	 } 
	 else {
		 document.getElementById('precioCompra').disabled = false;
		 document.getElementById('precioCompraIVA').disabled = true;
	 }
}