/*

Martus(TM) is a trademark of Beneficent Technology, Inc. 
This software is (c) Copyright 2015, Beneficent Technology, Inc.

Martus is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either
version 2 of the License, or (at your option) any later
version with the additions and exceptions described in the
accompanying Martus license file entitled "license.txt".

It is distributed WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, including warranties of fitness of purpose or
merchantability.  See the accompanying Martus License and
GPL license for more details on the required license terms
for this software.

You should have received a copy of the GNU General Public
License along with this program; if not, write to the Free
Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA 02111-1307, USA.

*/

package SAG;

public class AppConfiguration
{
	private String appName;
	private String appNameError;
	private String appIconLocation;
	private String appIconError;
	private String appXFormName;
	private String appXFormLocation;
	private String appXFormError;
	private String clientToken;
	private String clientTokenError;
	private String clientPublicKey;
	private String clientPublicCode;
	private String serverName;
	private String serverIP;
	private String serverPublicKey;
	private String apkLink;
	private String apkName;
	private int apkVersionMajor;
	private int apkVersionMinor;
	private int apkVersionBuild;
	private String apkBuildError;
	
	public void setAppName(String appName)
	{
		this.appName = appName;
	}
	
	public String getAppName()
	{
		return appName;
	}

	public String getAppNameError()
	{
		return appNameError;
	}

	public void setAppNameError(String appNameError)
	{
		this.appNameError = appNameError;
	}

	public String getAppIconLocation()
	{
		return appIconLocation;
	}

	public void setAppIconLocation(String appIconLocation)
	{
		this.appIconLocation = appIconLocation;
	}
	
	@Override
	public String toString()
	{
		return "Name: " + appName + ", Icon Loc: " + appIconLocation + ", XForm Loc: " + appXFormLocation;
	}

	public String getAppIconError()
	{
		return appIconError;
	}

	public void setAppIconError(String appIconError)
	{
		this.appIconError = appIconError;
	}

	public String getAppXFormName()
	{
		return appXFormName;
	}

	public void setAppXFormName(String appXFormName)
	{
		this.appXFormName = appXFormName;
	}

	public String getAppXFormLocation()
	{
		return appXFormLocation;
	}

	public void setAppXFormLocation(String appXFormLocation)
	{
		this.appXFormLocation = appXFormLocation;
	}

	public String getAppXFormError()
	{
		return appXFormError;
	}

	public void setAppXFormError(String appXFormError)
	{
		this.appXFormError = appXFormError;
	}

	public String getClientToken()
	{
		return clientToken;
	}

	public void setClientToken(String clientToken)
	{
		this.clientToken = clientToken;
	}

	public String getClientTokenError()
	{
		return clientTokenError;
	}

	public void setClientTokenError(String clientTokenError)
	{
		this.clientTokenError = clientTokenError;
	}

	public String getClientPublicKey()
	{
		return clientPublicKey;
	}

	public void setClientPublicKey(String clientPublicKey)
	{
		this.clientPublicKey = clientPublicKey;
	}

	public String getClientPublicCode()
	{
		return clientPublicCode;
	}

	public void setClientPublicCode(String clientPublicCode)
	{
		this.clientPublicCode = clientPublicCode;
	}	
	
	public String getApkLink()
	{
		return apkLink;
	}

	public void setApkLink(String apkLink)
	{
		this.apkLink = apkLink;
	}

	public String getServerName()
	{
		return serverName;
	}

	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}

	public String getServerIP()
	{
		return serverIP;
	}

	public void setServerIP(String serverIP)
	{
		this.serverIP = serverIP;
	}

	public String getServerPublicKey()
	{
		return serverPublicKey;
	}

	public void setServerPublicKey(String serverPublicKey)
	{
		this.serverPublicKey = serverPublicKey;
	}

	public String getApkName()
	{
		return apkName;
	}

	public void setApkName(String apkName)
	{
		this.apkName = apkName;
	}

	public int getApkVersionMajor()
	{
		return apkVersionMajor;
	}

	public void setApkVersionMajor(int apkVersionMajor)
	{
		this.apkVersionMajor = apkVersionMajor;
	}

	public int getApkVersionMinor()
	{
		return apkVersionMinor;
	}

	public void setApkVersionMinor(int apkVersionMinor)
	{
		this.apkVersionMinor = apkVersionMinor;
	}

	public int getApkVersionBuild()
	{
		return apkVersionBuild;
	}

	public void setApkVersionBuild(int apkVersionBuild)
	{
		this.apkVersionBuild = apkVersionBuild;
	}

	public String getApkBuildError()
	{
		return apkBuildError;
	}

	public void setApkBuildError(String apkBuildError)
	{
		this.apkBuildError = apkBuildError;
	}

}
