<script lang="ts">
    import {Button} from "$lib/components/ui/button";
    import {Textarea} from "$lib/components/ui/textarea";
    import ChatBubble from "../../../features/chat/ChatBubble.svelte";
    import {onMount} from "svelte";
    import type { PageData } from './$types';
    import LineMdLoadingAltLoop from '~icons/line-md/loading-alt-loop';

    let { data }: { data: PageData } = $props();

    let chatbot: WebSocket;
    let currentMessage = $state("")
    let messages = $state<{actor: "bot" | "human", message: string}[]>([]);
    let isActive = $state(false)
    let isThinking = $state(false);

    onMount(() => {
        // Initialize the WebSocket connection
        chatbot = new WebSocket(`ws://localhost:8080/chat/${data.id}`); // Change URL as needed

        // Handle WebSocket open event
        chatbot.onopen = () => {
            isActive = true;
        };

        // Handle incoming messages
        chatbot.onmessage = (event) => {
            const message = event.data;
            isThinking = false;
            messages.push({actor: 'bot', message});
        };

        // Handle WebSocket close event
        chatbot.onclose = () => {
            isActive = false;
        };

        // Handle WebSocket error
        chatbot.onerror = (error) => {
            console.error('WebSocket error:', error);
        };

    })

    // Send a message through the WebSocket
    function sendMessage(message:string) {
        if (chatbot && chatbot.readyState === WebSocket.OPEN && message.trim()) {
            messages.push({actor: "human", message});
            isThinking = true;
            currentMessage = '';
            chatbot.send(JSON.stringify({ message }));
        }
    }
</script>

<div class="p-8 h-full">
<h1>Hello {data.firstName} {data.lastName}</h1>
<div class="bg-gray-800 flex flex-col gap-4 w-full max-w-xl rounded-s p-4 h-full rounded-2xl">
    <ChatBubble variant="bot" actorName="chatbot">
        Hi {data.firstName}, ask me anything
    </ChatBubble>
    {#each messages as message}
        <ChatBubble
            variant={message.actor}
            actorName={message.actor === "human" ? data.firstName : "chatbot" }
    >
        {message.message}
    </ChatBubble>
    {/each}
    {#if isThinking}
        <LineMdLoadingAltLoop />
    {/if}
    <Textarea bind:value={currentMessage} placeholder="Type your message here." />
    <Button onclick={()=> {sendMessage(currentMessage)}}>send message</Button>
</div>
</div>

