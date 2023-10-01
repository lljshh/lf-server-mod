package org.ljieshuo.lfservermod.mixin;

import com.google.gson.JsonObject;
import net.minecraft.server.BanEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mixin(BanEntry.class)
public class ReWriteBan {
    @Shadow
    static SimpleDateFormat DATE_FORMAT;
    @Shadow
    Date creationDate;
    @Shadow
    String source;
    @Shadow
    Date expiryDate;
    @Shadow

    String reason;
    /**
     * @author Ljieshuo
     * @reason Let the source is "(Unknown)"
     */
    @Overwrite
    public String getSource() {
        return "(Unknown)";
    }
    /**
     * @author Ljieshuo
     * @reason Let the source is "(Unknown)"
     */
    @Overwrite
    protected void write(JsonObject json) {
        json.addProperty("created", this.DATE_FORMAT.format(this.creationDate));
        json.addProperty("source", this.source);
        json.addProperty("expires", this.expiryDate == null ? "forever" : this.DATE_FORMAT.format(this.expiryDate));
        json.addProperty("reason", this.reason);
    }
}
