<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" omit-xml-declaration="yes" indent="no" />
	<xsl:template match="/">
	   [<xsl:apply-templates select="//dependency" />]
	</xsl:template>


	<xsl:template match="/project//dependency[position() != count(//dependency)]">
        {"groupId":'<xsl:value-of select="./groupId/text()" />',
        "artifactId":'<xsl:value-of select="./artifactId/text()" />',
        "version":'<xsl:value-of select="./version/text()" />',
        },
	</xsl:template>
	 <xsl:template match="/project//dependency[position() = count(//dependency)]">
        {"groupId":'<xsl:value-of select="./groupId/text()" />',
        "artifactId":'<xsl:value-of select="./artifactId/text()" />',
        "version":'<xsl:value-of select="./version/text()" />',
        }
    </xsl:template>
</xsl:stylesheet>