<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

  <xsl:output method="xml" indent="yes"/>
  
  <xsl:template match="GraduatingStudents">
	<Enrollments>
	    <xsl:apply-templates select="Student/Courses/Course"/>
	</Enrollments>
  </xsl:template>

  <xsl:template match="Course">
	<CourseTaken
		sname="{../../SName}"
		gradyear="{../../GradYear}"
		title="{Title}"
		yeartaken="{YearTaken}"
		grade="{Grade}"
	/>
  </xsl:template>
 
</xsl:stylesheet>
