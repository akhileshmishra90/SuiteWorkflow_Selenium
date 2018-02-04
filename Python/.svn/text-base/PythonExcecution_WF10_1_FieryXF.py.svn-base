import sys;sys.path.append("C:\FieryXF")
from clientapp import *
from clientapp.reusablefunctions import *
app=autl.Application()
session = GetSession()                             
if app.Connect("EFI_XF_Client.exe") == -1:
      
    prg_path=self.GetInstallationPath()                          
    if app.Start(prg_path)!=-1:
        sleep(60)
        if app.WaitForWindow("(Fiery XF.*)|(Fiery proServer.*)",80):                                        
                if app.Connect("EFI_XF_Client.exe",600) == 0:
                        print "Connected to FieryXF Client"                           
                       
        elif app.WaitForWindow(wLogin,10):                                        
            loginwin=app.WaitForWindow(wLogin,10)
            if loginwin != None:
                print "Login window detected"                                 
                login= LoginWin(app)
                loginwin.Activate()
                sleep(3)
                login.UserName.SetText("admin")
                sleep(1)
                login.Password.SetText("admin")                                                            
                sleep(2)
                login.OK.Click()
                sleep(50)
                app.Disconnect()
                sleep(5)                
                if app.Connect("EFI_XF_Client.exe") == -1:
                    raise NameError ("Failed to login as Admin")
                else:
                    print "Admin login successful"                                                    
    else:                                        
        raise NameError ("Failed to start FieryXF Client")   

win= app.Window_()
win.Activate()
xf=FieryXFObj(app)
xf.TabCtrl.Select("Job Explorer")
je=JobExplorer(app)
tlc=je.JobList
tlc.GetRoot
troot=tlc.GetRoot()

item=tlc.FindItem(sys.argv[1], 2)
item.Click()

xf.PITabCtrl.Select("Output")
pc=PrintConfigurationPane(app)

pc.MediaConfigurationCollapse.Click()
sleep(2)
menu=pc.MediaConfigurationCollapse.GetActivePopupMenu()
menu.Select("QA")

xf=FieryXFObj(app)
xf.SelectMenu("File->Print")
WaitForPrint(je,session,"manetposter.pdf", sys.argv[1])


def GetInstallationPath(self,productname=None):
    prg_path = None    
    try:
        if sys.platform == 'win32' :
        # read from HKLM\r"SOFTWARE\Wow6432Node\Microsoft\Windows\CurrentVersion\Uninstall\{EF60966F-229E-4DCA-960A-FC0B625E3309}\InstallLocation ("C:\Program Files (x86)\EFI\Fiery XF)
        # add Client\EFI XF Client.exe
            if productname:
                    keyInstallLocation = winreg.OpenKey(winreg.HKEY_LOCAL_MACHINE, r"SOFTWARE\Wow6432Node\Microsoft\Windows\CurrentVersion\Uninstall\{EF50C2E7-AA19-4CF4-89A2-9CBAF66003D3}")
            else:
                    keyInstallLocation = winreg.OpenKey(winreg.HKEY_LOCAL_MACHINE, r"SOFTWARE\Wow6432Node\Microsoft\Windows\CurrentVersion\Uninstall\{EF60966F-229E-4DCA-960A-FC0B625E3309}")
                    
            tuplePath = winreg.QueryValueEx(keyInstallLocation, "InstallLocation")
            winreg.CloseKey(keyInstallLocation)
            if tuplePath != None :
                    strInstallLocation = tuplePath[0]
                    prg_path=str(strInstallLocation)+"\\Client\\EFI_XF_Client.exe"   
        elif sys.platform == 'darwin' :
            prg_path="/Applications/EFI Fiery XF/Client/Fiery XF Client.app"    
        if os.path.exists(prg_path)==False:
            return None
        return prg_path
    except WindowsError as e:
            print(e)
    return prg_path
