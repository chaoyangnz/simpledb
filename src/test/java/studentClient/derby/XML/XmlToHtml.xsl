<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

  <xsl:output method="html"/>
  
  <xsl:template match="GraduatingStudents">
	<html><body>
	<table border="1" cellpadding="2">
	<tr> <th>Name</th> <th>Grad Year</th> <th>Courses</th> </tr>
    <xsl:apply-templates select="Student"/>
    </table>
    </body></html>
  </xsl:template>

  <xsl:template match="Student">
	<tr> <td> <xsl:value-of select="SName"/> </td>
	     <td> <xsl:value-of select="GradYear"/> </td>
	     <td> <xsl:apply-templates select="Courses"/> </td>
	</tr>
  </xsl:template>
    
  <xsl:template match="Courses">
	<table border="0" cellpadding="2" width="100%">
	<tr> <th width="50%">Title</th> <th>Year</th> <th>Grade</th> </tr>
    <xsl:apply-templates select="Course"/>
    </table>
  </xsl:template>
  
  <xsl:template match="Course">
	<tr> <td> <xsl:value-of select="Title"/> </td>
	     <td> <xsl:value-of select="YearTaken"/>  </td>
	     <td> <xsl:value-of select="Grade"/> </td>
	</tr>
  </xsl:template>
 
</xsl:stylesheet>
