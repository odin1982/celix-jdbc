$(document).ready( function () {
    $('#table_producto').DataTable({
    	  "columnDefs": [
    		    { "width": "5%",  "targets": 0 },
    		    { "width": "10%", "targets": 1 },
    		    { "width": "10%",  "targets": 2 },
    		    { "width": "10%",  "targets": 3 },
    		    { "width": "10%",  "targets": 4 },
    		    { "width": "10%",  "targets": 5 },
    		    { "width": "10%",  "targets": 6 },
    		    { "width": "10%",  "targets": 7 },
    		    { "width": "10%",  "targets": 8 },
    		    { "width": "10%",  "targets": 9 },
    		    { "width": "5%",  "targets": 10 },
    		    { "width": "5%", "targets": 11,"orderable": false },
    		    { "width": "5%", "targets": 12,"orderable": false } 
    		  ]
    });
} );