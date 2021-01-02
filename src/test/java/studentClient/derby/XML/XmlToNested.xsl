<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

  <xsl:output method="xml" indent="yes"/>

  <xsl:template match="Enrollments">
	<GraduatingStudents>
    <xsl:for-each-group select="CourseTaken" group-by="@sname">
      <Student>
      	<SName><xsl:value-of select="@sname"/></SName>
      	<GradYear><xsl:value-of select="@gradyear"/></GradYear>
      	<Courses>
      		<xsl:for-each select="current-group()">
			  	<Course>
			  		<Title><xsl:value-of select="@title"/></Title>
					<YearTaken><xsl:value-of select="@yeartaken"/></YearTaken>
					<Grade><xsl:value-of select="@grade"/></Grade>
				</Course>
			</xsl:for-each>
      	</Courses>
      </Student>
    </xsl:for-each-group>
	</GraduatingStudents>
  </xsl:template>

</xsl:stylesheet>
