<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="layout/taglib/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>GreenInvoice! | Add Invoice</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/resources/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/resources/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="${pageContext.request.contextPath}/resources/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
	<!-- iCheck -->
    <link href="${pageContext.request.contextPath}/resources/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
	
    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/resources/build/css/custom.css" rel="stylesheet">
    
    <!-- Invoice Style -->
    <link href="${pageContext.request.contextPath}/resources/css/invoice.css" rel="stylesheet">
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>GreenInvoice</span></a>
            </div>
            <div class="clearfix"></div>
            <!-- menu profile quick info -->
            <%@include file="layout/menuprofile/menuprofile.jsp" %>
            <!-- /menu profile quick info -->
            <br/>
            <!-- sidebar menu -->
            <%@include file="layout/sidemenu/sidemenu.jsp" %>
            <!-- /sidebar menu -->
          </div>
        </div>

        <!-- top navigation -->
         <%@ include file="layout/navbar/topnavbar.jsp" %>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
        <input type="hidden" name="userId" value="${userId}">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Invoices</h3>
              </div>

              <div class="title_right">
                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                  </div>
                </div>
              </div>
            </div>
            <div class="clearfix"></div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Add Invoice<small>(fill the following details.)</small></h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br />
                    <form id="add-invoice-form" data-parsley-validate class="form-horizontal form-label-left">
                    <div class="row" id="invoice-number-row">
  					<div class="col-md-4 col-xs-12 col-sm-4 col-lg-4">
  						<label>Invoice Number</label>
                        <input id="invoice_number_text_input"type="text" class="form-control" value="${invoiceNumber}" readonly="readonly" >
					</div>
  						<div class="col-md-4 col-md-offset-4 col-xs-12 col-sm-4 col-sm-offset-4 col-lg-4 col-lg-offset-4"><label>Invoice Date</label>
                      	<div class="control-group">
						<div class="controls">
							<div class="col-md-9 xdisplay_inputx form-group has-feedback">
								<input type="text" class="form-control has-feedback-left"
									id="single_cal1" placeholder="Invoice Date"
									aria-describedby="inputSuccess2Status"> <span
									class="glyphicon glyphicon-calendar fa fa-calendar form-control-feedback left"
									aria-hidden="true"></span> <span id="inputSuccess2Status"
									class="sr-only">(success)</span>
							</div>
						</div>
						</div>
						</div>
					</div>
                    <div class="ln_solid"></div>
                    <label>Bill to</label>
                    <div class="row" id="customer-details-row">
                    <div class="item form-group col-md-8 col-sm-12 col-sm-12">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="name">Name <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input id="customer-name" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="name" placeholder="both name(s) e.g Steve Jobs" required="required" type="text">
                        </div>
                      </div>
                    <div class="item form-group col-md-4 col-sm-12 col-sm-12">
                      <label class="control-label col-md-4 col-sm-2 col-xs-12">Gender *:</label>
                      <p class="gender-radio-button">
                        M:
                        <input type="radio" class="flat" name="gender" id="genderM" value="M" checked="" required /> F:
                        <input type="radio" class="flat" name="gender" id="genderF" value="F" />
                      </p>
                    </div>
                   	</div>
                   	<div class="row" id="customer-address-line-1-row">
                   	<div class="item form-group col-md-8 col-sm-12 col-xs-12 ">
                   	<label class="control-label col-md-2 col-sm-2 col-xs-12" for="addressline1">Adress <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input id="address-line-1-input" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="addressline1" placeholder="enter the building no." required="required" type="text">
                        </div>
                   	</div>
                   	</div>
                   	<div class="row" id="customer-address-line-2-row">
                   	<div class="item form-group col-md-8 col-sm-12 col-xs-12 ">
                        <div class="col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-12">
                          <input id="address-line-2-input" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="addressline2" placeholder="enter the street name and area." required="required" type="text">
                        </div>
                   	</div>
                   	<div class="item form-group col-md-4 col-sm-12 col-sm-12">
                        <label class="control-label col-md-3 col-sm-2 col-xs-12">Country</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <select class="form-control" id="country-select">
                            <option>India</option>
                            <option>USA</option>
                            <option>Japan</option>
                            <option>Other</option>
                          </select>
                        </div>
                   	</div>
                   	</div>
                   	<div class="row" id="customer-address-line-2-row">
                   	<div class="item form-group col-md-8 col-sm-12 col-xs-12 ">
                        <div class="col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-12">
                          <input id="address-line-3-input" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" name="addressline3" placeholder="enter the city" required="required" type="text">
                        </div>
                   	</div>
                   	<div class="item form-group col-md-4 col-sm-12 col-sm-12">
                   	<label class="control-label col-md-3 col-sm-2 col-xs-12">Phone</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input type="text" class="form-control" data-inputmask="'mask' : '99-99999999'">
                          <span class="fa fa-phone form-control-feedback right" aria-hidden="true"></span>
                        </div>
                   	</div>
                   	</div>
                    <div class="ln_solid"></div>
                    
                    <!-- DATA TABLE -->
                    <button class="btn btn-primary" id="addRow" disabled="disabled">Add Row</button>
                    <button class="btn btn-primary" id="clearAll">clear All</button>
                    <div class="table-responsive">
                    <table id="item-table" class="display table-striped jambo_table" cellspacing="0" width="100%">
				        <thead>
				            <tr class="headings">
				                <th>Sr. No</th>
				                <th>Decription</th>
				                <th>Quantity</th>
				                <th>Rate</th>
				                <th>Amount</th>
				            </tr>
				        </thead>
				        <tbody>
				        </tbody>
				    </table>
				    </div>
                    <!-- DATA TABLE ENDS -->
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
						  <button class="btn btn-primary" type="reset">Reset</button>
                          <button type="submit" id="submit" class="btn btn-success" disabled="disabled">Submit</button>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="clearfix"></div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <%@ include file="layout/footer/footer.jsp" %>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${pageContext.request.contextPath}/resources/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${pageContext.request.contextPath}/resources/vendors/nprogress/nprogress.js"></script>
    <!-- Chart.js -->
    <script src="${pageContext.request.contextPath}/resources/vendors/Chart.js/dist/Chart.min.js"></script>
    <!-- jQuery Sparklines -->
    <script src="${pageContext.request.contextPath}/resources/vendors/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
    <!-- Flot -->
    <script src="${pageContext.request.contextPath}/resources/vendors/Flot/jquery.flot.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/Flot/jquery.flot.pie.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/Flot/jquery.flot.time.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/Flot/jquery.flot.stack.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/Flot/jquery.flot.resize.js"></script>
    <!-- Flot plugins -->
    <script src="${pageContext.request.contextPath}/resources/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/flot.curvedlines/curvedLines.js"></script>
    <!-- DateJS -->
    <script src="${pageContext.request.contextPath}/resources/vendors/DateJS/build/date.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="${pageContext.request.contextPath}/resources/vendors/moment/min/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    
    
    
    <!-- bootstrap-daterangepicker -->
    <script src="${pageContext.request.contextPath}/resources/vendors/moment/min/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- bootstrap-datetimepicker -->    
    <script src="${pageContext.request.contextPath}/resources/vendors/bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
    <!-- Ion.RangeSlider -->
    <script src="${pageContext.request.contextPath}/resources/vendors/ion.rangeSlider/js/ion.rangeSlider.min.js"></script>
    
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/build/js/custom.js"></script>
  
   <!-- validator -->
    <script src="${pageContext.request.contextPath}/resources/vendors/validator/validator.js"></script>
  
  	<!-- iCheck -->
    <script src="${pageContext.request.contextPath}/resources/vendors/iCheck/icheck.min.js"></script>
  	<!-- jquery.inputmask -->
    <script src="${pageContext.request.contextPath}/resources/vendors/jquery.inputmask/dist/min/jquery.inputmask.bundle.min.js"></script>
  
  	<!-- invoice Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/invoice.js"></script>
  
  	<!-- dataTable Scripts -->
    <script src="${pageContext.request.contextPath}/resources/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
  </body>
  
  
</html>