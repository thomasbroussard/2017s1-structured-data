<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
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

				<table>
					<thead>
						<tr>
							<th>groupId</th>
							<th>artifactId</th>
							<th>version</th>
						</tr>
					</thead>
                    <tbody>
                        <xsl:apply-templates select="//dependency"/>
                    </tbody>

                        
				</table>
			</body>
		</html>
	</xsl:template>


    <xsl:template match="dependency">
        <tr>
            <td><xsl:value-of select="./groupId/text()" /></td>
            <td><xsl:value-of select="./artifactId/text()"/></td>
            <td><xsl:value-of select="./version/text()" /></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>