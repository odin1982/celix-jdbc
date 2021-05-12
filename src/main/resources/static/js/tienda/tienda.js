$(document).ready( function () {
    $('#table_tienda').DataTable({
    	  "columnDefs": [
    		    { "width": "5%",  "targets": 0 },
    		    { "width": "20%", "targets": 1 },
    		    { "width": "35%", "targets": 2 },
    		    { "width": "10%", "targets": 3 },
    		    { "width": "10%", "targets": 4 },
    		    { "width": "10%", "targets": 5,"orderable": false },
    		    { "width": "10%", "targets": 6,"orderable": false } 
    		  ]
    });
} );