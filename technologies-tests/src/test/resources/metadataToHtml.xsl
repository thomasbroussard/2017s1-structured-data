<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" />
    
	<xsl:template match="metadata">
		<html>
			<head>
				<meta charset="ISO-8859-1"></meta>
				<title>Insert title here</title>

				<style type="text/css">
					td, th{
					border: 1px solid black;
					}

				</style>
			</head>
			<body>
                <form>
                      <xsl:apply-templates select="student"/>
                </form>
			</body>
		</html>
	</xsl:template>


    <xsl:template match="student" >
        <xsl:apply-templates select="property" />
    </xsl:template>
    
    <xsl:template match="property[@type='text']">
        <input type="text" >
            <xsl:attribute name="name" select="@name" />
        </input>
    </xsl:template>
</xsl:stylesheet>