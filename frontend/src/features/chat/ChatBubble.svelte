<script lang="ts" module>
    import type {Snippet} from "svelte";
    import LineMdEmojiGrinFilled from '~icons/line-md/emoji-grin-filled';
    export type ChatBubbleProps = {
        variant: "bot" | "human",
        actorName?: string,
        time?: Date
        status?: "Delivered" | "Pending" | "Error",
        children?:  Snippet<[]>
    }
</script>

<script lang="ts">
    let {
        variant,
        actorName,
        time,
        status,
        children
    }:ChatBubbleProps = $props();

    const variantStyle = variant === "bot"
        ? "border-gray-200 bg-orange-100 rounded-e-xl rounded-es-xl dark:bg-orange-700"
        : "border-gray-200 bg-blue-100 rounded-e-xl rounded-es-xl dark:bg-blue-700";
    const alignment = variant === "bot"
        ? "flex-row"
        : "flex-row-reverse";
</script>


<div class="flex items-start {alignment} gap-2.5">
    <LineMdEmojiGrinFilled/>
    <div class="flex flex-col w-full max-w-[320px] leading-1.5 p-4 {variantStyle}">
        <div class="flex items-center space-x-2 rtl:space-x-reverse">
            {#if actorName}
                <span class="text-sm font-semibold text-gray-900 dark:text-white">{actorName}</span>
            {/if}
            {#if time}
                <span class="text-sm font-normal text-gray-500 dark:text-gray-400">{time}</span>
            {/if}
        </div>
        <p class="text-sm font-normal py-2.5 text-gray-900 dark:text-white">{@render children?.()}</p>
        {#if status}
            <span class="text-sm font-normal text-gray-500 dark:text-gray-400">{status}</span>
        {/if}
    </div>
</div>
