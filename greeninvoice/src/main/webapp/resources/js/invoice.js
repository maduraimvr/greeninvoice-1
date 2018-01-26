
/**
 * Initialize method gets called automatically when script is loaded.
 * @returns
 */
$(document).ready(function() {
    var table = $('#item-table').DataTable();
 
    $('#submit').click( function() {
    	//check for single empty record ,then disable submit
    	if($('input', table.cell({ row: 0, column: 1 }).node()).val().trim() === ''){
    		checkDescriptionInput($('input', table.cell({ row: 0, column: 1 }).node()));
    	}
    	var tableSize =  $('#item-table tbody tr').length;
    	table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {       
            var descriptionCell = table.cell({ row: rowIdx, column: 1 }).node();
            console.log($('input', descriptionCell).val());
            if($('input', descriptionCell).val().trim()===''){
            	table.row(this).remove().draw(false);
            	var count=rowIdx;
            	console.log('row '+(count+1)+' removed as it is empty.');
            }
        });
    } );
    
    var table = $('#item-table').DataTable();
    var counter = 1;
    
    //click event for addRow button
    $('#addRow').on( 'click', function() {
    	constructTable(table,counter);
    	calculateAmount();
    	$('.table-input').bind('change', function(e) {
        	$(this).parent().attr('data-search',$(this).val());
        	$(this).parent().attr('data-order',$(this).val());
        	checkDescriptionInput($(this));
        });
        counter++;
	} );
    	
    // Automatically add a first row of data
    $('#addRow').click();
    
    //click event for clearAll button
    $('#clearAll').on( 'click', function () {
        table.clear().draw();
        counter = 1;
        constructTable(table,counter);
        calculateAmount();
        $('.table-input').bind('change', function(e) {
        	$(this).parent().attr('data-search',$(this).val());
        	$(this).parent().attr('data-order',$(this).val());
        	checkDescriptionInput($(this));
        });
        counter++;
    } );
    
    $('.table-input').bind('change', function(e) {
    	$(this).parent().attr('data-search',$(this).val());
    	$(this).parent().attr('data-order',$(this).val());
    	checkDescriptionInput($(this));
    });
    
} );

/**
 * calculateAmount method to multiply item quantity and item amount field
 * @void 
 */
function calculateAmount(){
	//change event for multiplying qunatity and rate to calculate amount
    $('.amount-input').bind('change', function(e) {
    	var rowNumber = $(this).attr('data-row-number');
    	var quantity = $('#item-quantity'+rowNumber).val();
    	var rate = $('#item-rate'+rowNumber).val();
    	quantity = quantity.replace(/[,]/g,'');
    	rate = rate.replace(/[₹ ,]/g,'');
    	var unformattedAmount =Math.round((quantity*rate) * 100) / 100; //rounded off to 2 decimal
    	var formattedAmount=formatAmount(unformattedAmount, "₹ ");
        $('#item-amount'+rowNumber).text(formattedAmount);
        $('#item-amount'+rowNumber).parent().attr('data-order',$('#item-amount'+rowNumber).text());
   	 	$('#item-amount'+rowNumber).parent().attr('data-search',$('#item-amount'+rowNumber).text());
    });
}

/**
 * constructTable method to dynamically construct a table row
 * @param table
 * @param counter
 */
function constructTable(table,counter){
	var itemDescription="<input id='item-description" +counter+
			"' class='form-control table-input description-input' data-row-number='" +counter+
			"'name='item-description" +counter+
			"' placeholder='Enter item description' required='required' type='text'>";
	var itemQunatity="<input id='item-quantity" +counter+
	"' class='form-control table-input amount-input' data-row-number='" +counter+
	"' name='item-quantity" +counter+
	"' placeholder='Enter item quantity' required='required' type='text'>";
	var itemRate="<input id='item-rate" +counter+
	"' class='form-control table-input amount-input' data-row-number='" +counter+
	"' name='item-rate" +counter+
	"' placeholder='Enter item rate' required='required' type='text'>";
	var itemAmount="<span id='item-amount" +counter+
			"' class ='item-amount' data-row-number='" +counter+
			"'/>";
	var rowElementArray=[counter,itemDescription,itemQunatity,itemRate,itemAmount];
	table.row.add(rowElementArray).draw( false );
	
	//to trigger pagination since it element is outside DOM
	if(counter==11||25||50||100){
		$('.next').click();
	}
	//initially set values for item quantity
	 $('#item-quantity'+counter).val(1);
	 
	//initially set values for item quantity
	 $('#item-rate'+counter).val(0);
	 
	//initially set values for serial number
	 $('#item-serial-number'+counter).text(counter);
	 $('#item-serial-number'+counter).parent().attr('data-order',$('#item-serial-number'+counter).text());
	 $('#item-serial-number'+counter).parent().attr('data-search',$('#item-serial-number'+counter).text());
	 
	//initially set values for item amount
	 $('#item-amount'+counter).text("₹ 0.00");
	 
	//inputmask for fixing item quantity
    $('#item-quantity'+counter).inputmask("numeric", {
        groupSeparator: ",",
        digits: 0,
        autoGroup: true,
        rightAlign: false
//        oncleared: function () { self.Value('1'); }
    });
	//inputmask for fixing item rate
    $('#item-rate'+counter).inputmask("numeric", {
        radixPoint: ".",
        groupSeparator: ",",
        digits: 2,
        autoGroup: true,
        prefix: '₹ ', //Space after $, this will not truncate the first character.
        rightAlign: false
//        oncleared: function () { self.Value('0.00'); }
    });
    
}

/**
 * formatAmount to format the currency amount
 * @param unformattedAmount
 * @param currency
 * @returns String
 */
function formatAmount(unformattedAmount, currency) {
    return currency + " " + unformattedAmount.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
}
/**
 * checkDescriptionInput method to check whether description is empty or not
 * @param event
 * @void
 */
function checkDescriptionInput(event){
	if(event.val().trim()===''){
		$('#submit').attr('disabled',true);
		$('#addRow').attr('disabled',true);
		event.val('');
	}else{
		$('#submit').attr('disabled',false);
		$('#addRow').attr('disabled',false);
	}
}


