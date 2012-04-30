package org.zanata.webtrans.client.events;

import org.zanata.webtrans.shared.auth.SessionId;
import org.zanata.webtrans.shared.model.Person;
import org.zanata.webtrans.shared.rpc.HasEnterWorkspaceData;

import com.google.gwt.event.shared.GwtEvent;

public class EnterWorkspaceEvent extends GwtEvent<EnterWorkspaceEventHandler> implements HasEnterWorkspaceData
{

   private final Person person;
   private final SessionId sessionId;

   /**
    * Handler type.
    */
   private static Type<EnterWorkspaceEventHandler> TYPE;

   /**
    * Gets the type associated with this event.
    * 
    * @return returns the handler type
    */
   public static Type<EnterWorkspaceEventHandler> getType()
   {
      if (TYPE == null)
      {
         TYPE = new Type<EnterWorkspaceEventHandler>();
      }
      return TYPE;
   }

   public EnterWorkspaceEvent(HasEnterWorkspaceData data)
   {
      this.person = data.getPerson();
      this.sessionId = data.getSessionId();
   }

   @Override
   protected void dispatch(EnterWorkspaceEventHandler handler)
   {
      handler.onEnterWorkspace(this);
   }

   @Override
   public Type<EnterWorkspaceEventHandler> getAssociatedType()
   {
      return getType();
   }

   @Override
   public Person getPerson()
   {
      return person;
   }

   @Override
   public SessionId getSessionId()
   {
      return sessionId;
   }
}
