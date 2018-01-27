
/**
 * Initialize method gets called automatically when script is loaded.
 * @returns
 */
$(document).ready(function() {
    var table = $('#item-table').DataTable();
 
    
    //set default and tax amount values
    $('#tax-text-input').val('0.00 %');
    
    $('#shipping-charges-text-input').val("₹ 0.00");
    
  //inputmask for fixing tax-input-charges
    $('#tax-text-input').inputmask("numeric", {
        radixPoint: ".",
        groupSeparator: ",",
        digits: 2,
        autoGroup: true,
        max:100,
        suffix: ' %', //Space after $, this will not truncate the first character.
        rightAlign: true
    });
    
    //inputmask for fixing shipping-charges
    $('#shipping-charges-text-input').inputmask("numeric", {
        radixPoint: ".",
        groupSeparator: ",",
        digits: 2,
        autoGroup: true,
        prefix: '₹ ', //Space after $, this will not truncate the first character.
        rightAlign: true
    });
    
    
    $('#tax-text-input').bind('change', function(e) {
    	if($('#tax-text-input').val()==''){
    		$('#tax-text-input').val('0.00 %');
    	}
    	var unformattedTax=$('#tax-text-input').val();
    	if(unformattedTax!=='100 %'){
    	unformattedTax=unformattedTax.replace(/[% ,]/g,'');
    	$('#tax-text-input').val(parseFloat(unformattedTax).toFixed(2));
    	}else{
    		$('#tax-text-input').val(unformattedTax);
    	}
    	calculateTotalBillAmount();
    });
    
    $('#shipping-charges-text-input').bind('change', function(e) {
    	if($('#shipping-charges-text-input').val()==''){
    		$('#shipping-charges-text-input').val('₹ 0.00');
    	}
    	var unformattedAmount=$('#shipping-charges-text-input').val();
    	unformattedAmount=unformattedAmount.replace(/[₹ ,]/g,'');
    	var formattedAmount=formatAmount(parseFloat(unformattedAmount), "₹ ");
    	$('#shipping-charges-text-input').val(formattedAmount);
    	calculateTotalBillAmount();
    });
    
    
    //event for sumbit button
    $('#submit').click( function() {
    	//check for single empty record ,then disable submit
    	if($('input', table.cell({ row: 0, column: 1 }).node()).val().trim() === ''){
    		checkDescriptionInput($('input', table.cell({ row: 0, column: 1 }).node()));
    	}
    	var tableSize =  $('#item-table tbody tr').length;
    	table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {       
            var descriptionCell = table.cell({ row: rowIdx, column: 1 }).node();
            if($('input', descriptionCell).val().trim()===''){
            	table.row(this).remove().draw(false);
            	var count=rowIdx;
            	console.log('row '+(count+1)+' removed as it is empty.');
            }
        });
    	calculateTotalBillAmount();
    	callAjax();
    } );
    /**
     * 
     */
    function callAjax() {
    	var dataParam=getData();
        var ajaxurl = window.location.href+'/submit';
        if (ajaxurl && ajaxurl.length>0) {
        	 var _getData = getData();
                var ajax_args = {
                	headers: { 'Accept': 'application/json',
                            'Content-Type': 'application/json' 
                        },
                    url: ajaxurl,
                    type: "POST",
                    data: JSON.stringify(_getData),
                    dataType: "json",
                    beforeSend: function(){
//                        $this.loader.show();
                    },
                    error: function(){
//                        $this.loader.hide();
                    },
                    success: function(res){
//                        $this.loader.hide();
                        if(res.isSuccessful){
                        	 console.log('sucesssss');
                        }
                    }
                };
                $.ajax(ajax_args);
	}
    }
    
    /**
     * get All data from the components in page.
     */
    function getData() {
    	var dataParam={};
    	var invoiceNumber = $('#invoice-number-text-input').val();
		var invoiceDate = $('.invoice-date-picker').val();
		var customerName = $('#customer-name-text-input').val();
		var gender = $('input[name=gender]:checked', '.gender-radio-button').val();
		var addressLine1 = $('#address-line-1-input').val();
		var addressLine2 = $('#address-line-2-input').val();
		var addressLine3 = $('#address-line-3-input').val();
		var country =  $('select#country-select option:selected').val();
		var phoneNumber = $('#phone-number-input').val();
		var taxPercentage = $('#tax-text-input').val();
		var shippingCharges = $('#shipping-charges-text-input').val();
		var totalTable = $('#total-table').DataTable();
		var subTotalAmount = $('#sub-total').text();
		var taxAmount = $('#tax-charges').text();
		var totalBillAmount = $('#total-bill-amount').text();
		var table = $('#item-table').DataTable();
		var tableData=[];
		table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {  
			var rowData={};
			var serialNumberValue = (rowIdx+1);
            var itemDescriptionCell = table.cell({ row: rowIdx, column: 1 }).node();
            var itemDescriptionValue = $('input', itemDescriptionCell).val();
            var itemQuantityCell = table.cell({ row: rowIdx, column: 2 }).node();
            var itemQuantityValue = $('input', itemQuantityCell).val();
            var itemRateCell = table.cell({ row: rowIdx, column: 3 }).node();
            var itemRateValue = $('input', itemRateCell).val();
            var itemAmountCell = table.cell({ row: rowIdx, column: 4 }).node();
            var itemAmountValue = $('span', itemAmountCell).text();
            rowData={
            		'serialNumber':serialNumberValue,
            		'itemDescription':itemDescriptionValue,
            		'itemQuantity':itemQuantityValue,
            		'itemRate':itemRateValue,
            		'amount':itemAmountValue
            }
            tableData.push(rowData);
        });
		dataParam={
				'invoiceNumber':invoiceNumber,
				'invoiceDate':invoiceDate,
				'customerName':customerName,
				'gender':gender,
				'addressLine1':addressLine1,
				'addressLine2':addressLine2,
				'addressLine3':addressLine3,
				'country':country,
				'phoneNumber':phoneNumber,
				'itemDetailsList':tableData,
				'taxPercentage':taxPercentage,
				'shippingCharges':shippingCharges,
				'subTotalAmount':subTotalAmount,
				'taxAmount':taxAmount,
				'totalBillAmount':totalBillAmount
				
		}
		return dataParam;
		console.log(dataParam);
	}
    
    
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
        calculateTotalBillAmount();
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
   	 	calculateTotalBillAmount();
    });
}
/**
 * calculateTotalBillAmount to calculate total bill amount
 * 
 * @returns
 */
function calculateTotalBillAmount(){
	var table = $('#item-table').DataTable();
	var totalTable = $('#total-table').DataTable();
	var tax = $('#tax-text-input').val();
	var getUnformattedTax=tax.replace(/[% ,]/g,'');
	var shippingCharges = $('#shipping-charges-text-input').val();
	var getUnformattedshippingCharges = shippingCharges.replace(/[₹ ,]/g,'');
	var formattedshippingCharges=parseFloat(getUnformattedshippingCharges);
	var totalAmount=0;
	table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {       
        var itemAmountCell = table.cell({ row: rowIdx, column: 4 }).node();
        var itemAmountValue = $('span', itemAmountCell).text();
        var getUnformattedAmount = itemAmountValue.replace(/[₹ ,]/g,'');
        totalAmount=parseFloat(getUnformattedAmount)+parseFloat(totalAmount);
    });
	var formattedTotalAmount=formatAmount(totalAmount, "₹ ");
	$('#sub-total').text(formattedTotalAmount);
	
	var taxAmount=((parseFloat(getUnformattedTax).toFixed(2)*totalAmount)/100);
	$('#tax-charges').text(formatAmount(taxAmount, "₹ "));
	
	$('#shipping-charges').text(shippingCharges);
	
	var totalBillAmount=parseFloat(totalAmount+taxAmount+formattedshippingCharges).toFixed(2);
	
	$('#total-bill-amount').text(formatAmount(totalBillAmount, "₹ "));
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
    return currency + " " + parseFloat(unformattedAmount).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
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


