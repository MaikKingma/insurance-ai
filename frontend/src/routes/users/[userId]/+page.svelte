<script lang="ts">
    import {Button} from "$lib/components/ui/button";
    import {Textarea} from "$lib/components/ui/textarea";
    import ChatBubble from "../../../features/chat/ChatBubble.svelte";
    import {onMount} from "svelte";
    import type { PageData } from './$types';
    import LineMdLoadingAltLoop from '~icons/line-md/loading-alt-loop';
    import LineMdChevronRightCircle from '~icons/line-md/chevron-right-circle';

    let { data }: { data: PageData } = $props();

    let chatbot: WebSocket;
    let currentMessage = $state("")
    let messages = $state<{actor: "bot" | "human", message: string}[]>([]);
    let isActive = $state(false)
    let isThinking = $state(false);

    onMount(() => {
        // Initialize the WebSocket connection
        chatbot = new WebSocket(`ws://localhost:8080/websocket/${data.id}`); // Change URL as needed

        // Handle WebSocket open event
        chatbot.onopen = () => {
            isActive = true;
        };

        // Handle incoming messages
        chatbot.onmessage = (event) => {
            const data = JSON.parse(event.data);
            if(data.topic === "CHAT") {
                isThinking = false;
                messages.push({actor: 'bot', message: data.message});
            }
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
    function sendMessage() {
        if (chatbot && chatbot.readyState === WebSocket.OPEN && currentMessage.trim()) {
            messages.push({actor: "human", message: currentMessage});
            chatbot.send(JSON.stringify({
                topic: "CHAT",
                userid: data.id,
                message: currentMessage,
            }));
            isThinking = true;
            currentMessage = '';
        }
    }

    function handleEnter(e:KeyboardEvent) {
        if(e.key === "Enter") {
            e.preventDefault()
            sendMessage();
        }
    }
</script>

<div class="p-8 h-full">
<h1>Hello {data.firstName} {data.lastName}</h1>
<div class="bg-gray-800 flex flex-col gap-4 w-full max-w-xl rounded-s p-4 h-full rounded-2xl">
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
    <div class="flex gap-2">
    <Textarea class="flex-auto border border-white" bind:value={currentMessage} onkeydown={handleEnter} placeholder="Type your message here." />
    <Button class="h-auto bg-blue-600 text-white hover:text-blue-600 text-2xl" onclick={sendMessage}><LineMdChevronRightCircle style="width: 1.5rem; height: 1.5rem" /></Button>
    </div>
</div>
</div>

