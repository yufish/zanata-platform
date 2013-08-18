package org.zanata.action;

import java.io.InputStream;
import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.security.Identity;
import org.zanata.ApplicationConfiguration;
import org.zanata.async.AsyncHandle;
import org.zanata.async.tasks.ZipFileBuildTask;
import org.zanata.dao.ProjectIterationDAO;
import org.zanata.process.IterationZipFileBuildProcess;
import org.zanata.process.IterationZipFileBuildProcessHandle;
import org.zanata.process.ProcessHandle;
import org.zanata.service.ProcessManagerService;
import org.zanata.service.impl.AsyncTaskManagerServiceImpl;

@Name("projectIterationZipFileAction")
@Scope(ScopeType.CONVERSATION)
public class ProjectIterationZipFileAction implements Serializable
{

   private static final long serialVersionUID = 1L;

   @In
   private ProjectIterationDAO projectIterationDAO;

   @In
   AsyncTaskManagerServiceImpl asyncTaskManagerServiceImpl;

   @In
   private ApplicationConfiguration applicationConfiguration;

   private String projectSlug;

   private String iterationSlug;

   private String localeId;

   private AsyncHandle<InputStream> zipFilePrepHandle;

   @Begin(join = true)
   @Restrict("#{s:hasPermission(projectIterationZipFileAction.projectIteration, 'download-all')}")
   public void prepareIterationZipFile(boolean offlinePo)
   {
      if( this.zipFilePrepHandle != null && !this.zipFilePrepHandle.isDone() )
      {
         // Cancel any other processes for this conversation
         this.zipFilePrepHandle.cancel(true);
      }

      // Start a zip file task
      ZipFileBuildTask task = new ZipFileBuildTask(this.projectSlug, this.iterationSlug, this.localeId,
            Identity.instance().getCredentials().getUsername(), offlinePo);

      asyncTaskManagerServiceImpl.startTask(task);
   }

   @End
   public void cancelFileDownload()
   {
      this.zipFilePrepHandle.cancel(false);
   }

   public Object getProjectIteration()
   {
      return this.projectIterationDAO.getBySlug(this.projectSlug, this.iterationSlug);
   }

   public String getProjectSlug()
   {
      return projectSlug;
   }

   public void setProjectSlug(String projectSlug)
   {
      this.projectSlug = projectSlug;
   }

   public String getIterationSlug()
   {
      return iterationSlug;
   }

   public void setIterationSlug(String iterationSlug)
   {
      this.iterationSlug = iterationSlug;
   }

   public String getLocaleId()
   {
      return localeId;
   }

   public void setLocaleId(String localeId)
   {
      this.localeId = localeId;
   }

   public AsyncHandle<InputStream> getZipFilePrepHandle()
   {
      return zipFilePrepHandle;
   }

   public void setZipFilePrepHandle(AsyncHandle<InputStream> zipFilePrepHandle)
   {
      this.zipFilePrepHandle = zipFilePrepHandle;
   }
}
