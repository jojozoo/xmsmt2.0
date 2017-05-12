<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	SimpleDateFormat dateFormat		= new SimpleDateFormat("yyMMdd_HHmmssSSS");
	String requestid				= "ZGTPAY" + dateFormat.format(new Date());
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>订单支付接口</title>

	<style type="text/css">
		tr.onekeyClose {
			display: none;
		}

		tr.directClose {
			display: none;
		}

		tr.commonClose {
			display: none;
		}

		tr.usernoClose {
			display: none;
		}
		
		tr.mccClose {
			display: none;
		}

		tr.bindidClose {
			display: none;
		}
	</style>

	<script type="text/javascript">

		function closeOnekey() {
			document.getElementById('onekey01').className='onekeyClose';
		}

		function openOnekey() {
			document.getElementById('onekey01').className='';
		}

		function closeDirect() {
			document.getElementById('direct01').className='directClose';
			document.getElementById('direct02').className='directClose';
			document.getElementById('direct03').className='directClose';
			document.getElementById('direct04').className='directClose';
		}

		function openDirect() {
			document.getElementById('direct01').className='';
			document.getElementById('direct02').className='';
			document.getElementById('direct03').className='';
			document.getElementById('direct04').className='';
		}

		function closeCommon() {
			document.getElementById('common01').className='commonClose';
			document.getElementById('common02').className='commonClose';
			document.getElementById('common03').className='commonClose';
		}

		function openCommon() {
			document.getElementById('common01').className='';
			document.getElementById('common02').className='';
			document.getElementById('common03').className='';
		}

		function closeBindid() {
			document.getElementById('bindidTr').className='bindidClose';
		}

		function openBindid() {
			document.getElementById('bindidTr').className='';
		}

		function closeUserno() {
			document.getElementById('usernoTr').className='usernoClose';
		}

		function openUserno() {
			document.getElementById('usernoTr').className='';
		}

		function closeMcc() {
			document.getElementById('mccTr').className='mccClose';
		}

		function openMcc() {
			document.getElementById('mccTr').className='';
		}

		function clean() {
			document.getElementById('userno').value='';
			document.getElementById('ip').value='';
			document.getElementById('isbind').value='';
			document.getElementById('bindid').value='';
			document.getElementById('cardname').value='';
			document.getElementById('idcard').value='';
			document.getElementById('bankcardnum').value='';
			document.getElementById('mobilephone').value='';
			document.getElementById('cvv2').value='';
			document.getElementById('expiredate').value='';
			document.getElementById('mcc').value='7993';
		}

	</script>
	
	
</head>
	<body>
		<table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" style="border:solid 1px #107929">
			<tr>
		  		<th align="center" height="30" colspan="5" bgcolor="#6BBE18">
					请输入订单请求参数	
				</th>
		  	</tr> 

			<form method="post" action="sendPay.jsp" target="_blank" accept-charset="UTF-8">
				<tr >
					<td width="20%" align="left">&nbsp;商户订单号</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="requestid" value="<%=requestid%>"/>
						<span style="color:#FF0000;font-weight:100;">*</span>
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">requestid</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;支付金额</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="amount" value="0.01" />
						<span style="color:#FF0000;font-weight:100;">*</span>
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">amount</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;是否担保</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input type="radio" name="assure" id="assure_0" value="0" checked />
						<label for="assure_0">非担保交易</label>
						<input type="radio" name="assure" id="assure_1" value="1"/>
						<label for="assure_1">担保交易</label>
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">assure</td> 
				</tr> 

				<tr >
					<td width="20%" align="left">&nbsp;商品名称</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="productname" value="productname哈哈测试喵喵喵" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">productname</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;商品种类</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="productcat" value="productcat" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">productcat</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;商品描述</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="productdesc" value="productdesc" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">productdesc</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;分账详情</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="divideinfo" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">divideinfo</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;后台通知地址</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="callbackurl" value="http://localhost:8080/zgt-java/jsp/payCallback.jsp" />
						<span style="color:#FF0000;font-weight:100;">*</span>
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">callbackurl</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;页面通知地址</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="webcallbackurl" value="http://localhost:8080/zgt-java/jsp/payCallback.jsp" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">webcallbackurl</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;银行编码</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="bankid" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">bankid</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;担保有效期时间</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="period" placeholder="担保交易时必填，最大值30。"value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">period</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;订单备注</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="memo" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">memo</td> 
				</tr>

				<tr >
					<td width="20%" align="left">&nbsp;支付类型</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input type="radio" name="payproducttype" id="sales_payproducttype" value="SALES" 
							   onclick= "clean(); closeOnekey(); closeDirect(); closeCommon(); closeUserno(); closeBindid(); closeMcc();" checked />
						<label for="SALES">网银支付</label>

						<input type="radio" name="payproducttype" id="onekey_payproducttype" value="ONEKEY"
							   onclick="clean(); openOnekey(); openCommon(); closeDirect(); openUserno(); closeBindid(); closeMcc();"/>
						<label for="ONEKEY">手机一键</label>

						<input type="radio" name="payproducttype" id="direct_payproducttype01" value="DIRECT"
							   onclick="clean(); openDirect(); openCommon(); closeOnekey(); openUserno(); closeBindid(); openMcc();"/>
						<label for="DIRECT">无卡直连-首次支付</label>

						<input type="radio" name="payproducttype" id="direct_payproducttype02" value="DIRECT"
							   onclick="clean(); closeDirect(); openUserno(); openBindid(); closeCommon(); closeOnekey(); openMcc();"/>
						<label for="DIRECT">无卡直连-绑卡支付</label>
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">payproducttype</td> 
				</tr> 

				<tr class="usernoClose" id="usernoTr">
					<td width="20%" align="left">&nbsp;用户标识</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="userno" id="userno" placeholder="无卡直连-绑卡支付时必填！" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">userno</td> 
				</tr>

				<tr class="bindidClose" id="bindidTr">
					<td width="20%" align="left">&nbsp;绑卡ID</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="bindid" id="bindid" placeholder="无卡直连-绑卡支付时必填！" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">bindid</td> 
				</tr>

				<tr class="directClose" id="direct01">
					<td width="20%" align="left">&nbsp;是否绑卡</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="isbind" id="isbind" placeholder="N-不绑卡；Y-绑卡；空-不绑卡。">
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">isbind</td> 
				</tr>

				<tr class="onekeyClose" id="onekey01">
					<td width="20%" align="left">&nbsp;用户IP</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="ip" id="ip" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">ip</td> 
				</tr>
				
				<tr class="commonClose" id="common01">
					<td width="20%" align="left">&nbsp;持卡人姓名</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="cardname" id="cardname" placeholder="无卡直连-首次支付时，必填！" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">cardname</td> 
				</tr>

				<tr class="commonClose" id="common02">
					<td width="20%" align="left">&nbsp;身份证号</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="idcard" id="idcard" placeholder="无卡直连-首次支付时，必填！" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">idcard</td> 
				</tr>

				<tr class="commonClose" id="common03">
					<td width="20%" align="left">&nbsp;银行卡号</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="bankcardnum" id="bankcardnum" placeholder="无卡直连-首次支付时，必填！" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">bankcardnum</td> 
				</tr>
	
				<tr class="directClose" id="direct02">
					<td width="20%" align="left">&nbsp;预留手机号</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="mobilephone" id="mobilephone" placeholder="无卡直连-首次支付时，必填！" value="" />
						<span style="color:#FF0000;font-weight:100;">*</span>
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">mobilephone</td> 
				</tr>

				<tr class="directClose" id="direct03">
					<td width="20%" align="left">&nbsp;cvv2</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="cvv2" id="cvv2" placeholder="信用卡支付时必填" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">cvv2</td> 
				</tr>

				<tr class="directClose" id="direct04">
					<td width="20%" align="left">&nbsp;信用卡有效期</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="expiredate" id="expiredate" placeholder="信用卡支付时必填" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">expiredate</td> 
				</tr>

				<tr class="mccClose" id="mccTr">
					<td width="20%" align="left">&nbsp;行业代码</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="mcc" id="mcc" placeholder="无卡直连时必填；测试商编mcc为7993" />
						<span style="color:#FF0000;font-weight:100;">*</span>
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">mcc</td> 
				</tr>

				<%--tr >
					<td width="20%" align="left">&nbsp;地区码</td>
					<td width="5%"  align="center"> : &nbsp;</td> 
					<td width="55%" align="left"> 
						<input size="70" type="text" name="areacode" value="" />
					</td>
					<td width="5%"  align="center"> - </td> 
					<td width="15%" align="left">areacode</td> 
				</tr--%>

				<tr >
					<td width="20%" align="left">&nbsp;</td>
					<td width="5%"  align="center">&nbsp;</td> 
					<td width="55%" align="left"> 
						<input type="submit" value="提交订单" />
					</td>
					<td width="5%"  align="center">&nbsp;</td> 
					<td width="15%" align="left">&nbsp;</td> 
				</tr>

			</form>
		</table>
</body>
</html>
