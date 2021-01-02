<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  version="2.0">

  <xsl:output method="text"/>
  
  <xsl:template match="GraduatingStudents">
    <xsl:apply-templates select="Student"/>
	<xsl:apply-templates select="Student/Courses/Course"/>
  </xsl:template>

  <xsl:template match="Student">
  	insert into APPLICANT (AppName, GradYear) values(
	'<xsl:value-of select="SName"/>', <xsl:value-of select="GradYear"/> );
  </xsl:template>
  
  <xsl:template match="Course">
	insert into COURSE_TAKEN(AppName, Title, Grade) values(
	'<xsl:value-of select="../../SName"/>',
	'<xsl:value-of select="Title"/>',
	'<xsl:value-of select="Grade"/>');
  </xsl:template>
 
</xsl:stylesheet>
