<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>Hello WebService Enthusiast</title>
</head>

<body bgcolor= "A17FF2" background="http://3.bp.blogspot.com/-12pu9NQbneM/T3NjGj1-WWI/AAAAAAAAJPc/jK6CRtrATaE/s1600/Dracunculiasis_Flag_of_WHO.png">
<div>
<table>
<tr height="25%">
<td valign="top">
 <h3> List of Doctors:</h3> <br> <s:property escapeHtml="false" value="listOfDoctors"/>
  </td>
  <td width= "10%"></td>
  <td width= "10%"></td>
  <td valign="top">
 <h3> List of Hospitals: </h3><br> <s:property escapeHtml="false" value="hospitalsName"/>
  </td>
  </tr>
 <h3> Disease description: </h3><br> <s:property escapeHtml="false" value="diseaseDescription"/>
 </table>
 </div>
</body>
</html>