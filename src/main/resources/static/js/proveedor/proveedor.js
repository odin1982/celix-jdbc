$(document).ready( function () {
    $('#table_proveedor').DataTable({
    	  "columnDefs": [
    		    { "width": "5%",  "targets": 0 },
    		    { "width": "10%", "targets": 1 },
    		    { "width": "10%", "targets": 2 },
    		    { "width": "10%", "targets": 3 },
    		    { "width": "10%", "targets": 4 },
    		    { "width": "10%", "targets": 5 },
    		    { "width": "10%", "targets": 6 },
    		    { "width": "10%", "targets": 7 },
    		    { "width": "5%", "targets": 8,"orderable": false },
    		    { "width": "5%", "targets": 9,"orderable": false } 
    		  ],
    		  "language": {
  		        url: '../DataTables/es-mx.json'
  		    }
    });
} );