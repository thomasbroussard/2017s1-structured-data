<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" />

    <xsl:param name="urlVariable"/>   
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
				<form method="get">
					<xsl:attribute name="action">  
					   <xsl:value-of select="$urlVariable" />
					</xsl:attribute>
					<xsl:apply-templates select="student" />
					<input type="submit" />
				</form>
			</body>
		</html>
	</xsl:template>


	<xsl:template match="student">
		<xsl:apply-templates select="property" />

	</xsl:template>

	<xsl:template match="property[@type='text']">
		<div>
			<label>
				<xsl:value-of select="@label" />
			</label>
			<input type="text">
				<xsl:attribute name="name">
                    <xsl:value-of select="@name" />
				</xsl:attribute>
			</input>
		</div>
	</xsl:template>
	
	<xsl:template match="property[@type='date']">
        <div>
            <label>
                <xsl:value-of select="@label" />
            </label>
            <input type="date">
                <xsl:attribute name="name">
                    <xsl:value-of select="@name" />
                </xsl:attribute>
            </input>
        </div>
    </xsl:template>
    
        <xsl:template match="property[@type='email']">
        <div>
            <label>
                <xsl:value-of select="@label" />
            </label>
            <input type="email">
                <xsl:attribute name="name">
                    <xsl:value-of select="@name" />
                </xsl:attribute>
            </input>
        </div>
    </xsl:template>
</xsl:stylesheet>