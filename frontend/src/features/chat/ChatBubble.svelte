<script lang="ts" module>
    import type {Snippet} from "svelte";
    import { fly } from 'svelte/transition';
    import LineMdEmojiGrinFilled from '~icons/line-md/emoji-grin-filled';
    import BxsBot from '~icons/bxs/bot';
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

    const isBot = variant === "bot";

    const variantStyle = isBot
        ? "border-gray-200 bg-teal-100 rounded-e-xl rounded-es-xl dark:bg-teal-700"
        : "border-gray-200 bg-blue-100 rounded-e-xl rounded-es-xl dark:bg-blue-700";
    const alignment = isBot
        ? "flex-row"
        : "flex-row-reverse";
</script>

<style>
    .pre-wrap {
        white-space: pre-wrap;
    }
</style>


<div in:fly={{ y: 100 }} class="flex items-start {alignment} gap-2.5">
    <div class="pt-2">
    {#if isBot}
        <BxsBot />
    {:else}
        <LineMdEmojiGrinFilled/>
    {/if}
    </div>
    <div class="flex flex-col max-w-[320px] leading-1.5 py-2 px-4 {variantStyle}">
        <div class="flex items-center space-x-2 rtl:space-x-reverse">
            {#if actorName}
                <span class="text-sm font-semibold text-gray-900 dark:text-white">{actorName}</span>
            {/if}
            {#if time}
                <span class="text-sm font-normal text-gray-500 dark:text-gray-400">{time}</span>
            {/if}
        </div>
        <p class="text-sm font-normal py-2.5 pre-wrap text-gray-900 dark:text-white">{@render children?.()}</p>
        {#if status}
            <span class="text-sm font-normal text-gray-500 dark:text-gray-400">{status}</span>
        {/if}
    </div>
</div>
