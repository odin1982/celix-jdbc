$(document).ready( function () {
    $('#table_marca').DataTable({
    	  "columnDefs": [
    		    { "width": "5%",  "targets": 0 },
    		    { "width": "75%", "targets": 1 },
    		    { "width": "10%", "targets": 2,"orderable": false },
    		    { "width": "10%", "targets": 3,"orderable": false } 
    		  ]
    });
} );