<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

  <xsl:output method="xml" indent="yes"/>
  
  <xsl:template match="GraduatingStudents">
	<StudentNames>
    	<xsl:apply-templates select="Student"/>
    </StudentNames>
  </xsl:template>

  <xsl:template match="Student">
	<Name><xsl:value-of select="SName"/></Name>
  </xsl:template>
    
</xsl:stylesheet>
