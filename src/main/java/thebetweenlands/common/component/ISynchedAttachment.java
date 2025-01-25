package thebetweenlands.common.component;

public interface ISynchedAttachment<T extends ISynchedAttachment<T>> {

	SynchedAttachmentType<T> getSynchedAttachmentType(AttachmentHolderIdentifier<?> attachee);

}