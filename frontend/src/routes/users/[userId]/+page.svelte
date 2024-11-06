<script lang="ts">
    import {Button} from "$lib/components/ui/button";
    import {Textarea} from "$lib/components/ui/textarea";
    import {onMount} from "svelte";
    import LineMdChevronRightCircle from '~icons/line-md/chevron-right-circle';
    import SvgSpinners3DotsFade from '~icons/svg-spinners/3-dots-fade';
    import ChatBubble from "../../../features/chat/ChatBubble.svelte";
    import ProductBasket from "../../../features/products/ProductBasket.svelte";
    import type {PageData} from './$types';

    let {data}: { data: PageData } = $props();

    let chatbot: WebSocket;
    let chatbox: Element;
    let currentMessage = $state("")
    let messages = $state<{ actor: "bot" | "human", message: string }[]>([]);
    let isActive = $state(false)
    let isThinking = $state(false);


    $effect(() => {
        if (messages.length > 0) {

            chatbox?.scrollTo({top: chatbox.scrollHeight, behavior: 'smooth'})
        }
    })

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
            if (data.topic === "CHAT") {
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
                userId: data.id,
                message: currentMessage,
            }));
            isThinking = true;
            currentMessage = '';
        }
    }

    function handleEnter(e: KeyboardEvent) {
        if (e.key === "Enter") {
            e.preventDefault()
            sendMessage();
        }
    }
</script>

<h1 class="text-xl px-8 pt-8">Hello {data.firstName} {data.lastName}</h1>
<div class="p-8 h-full flex gap-8">
    <div bind:this={chatbox}
         class="bg-gray-100 dark:bg-gray-800 flex flex-col gap-4 w-full max-w-xl rounded-s p-4 h-full rounded-2xl overflow-auto max-h-[80dvh] relative">

        {#each messages as message}
            <ChatBubble
                    variant={message.actor}
                    actorName={message.actor === "human" ? data.firstName : "chatbot" }
            >
                {message.message}
            </ChatBubble>
        {/each}
        {#if isThinking}
            <span class="px-2 bg-teal-100 dark:bg-teal-700 rounded-full max-w-10"><SvgSpinners3DotsFade/></span>
        {/if}
        <div class="flex gap-2 bg-white dark:bg-gray-900 p-2 bottom-0 left-0 right-0 sticky rounded-xl">
            <Textarea class="flex-auto border border-gray-100 dark:border-gray-700" bind:value={currentMessage}
                      onkeydown={handleEnter} placeholder="Type your message here."/>
            <Button class="h-auto bg-blue-600 text-white hover:text-blue-600 text-2xl" onclick={sendMessage}>
                <LineMdChevronRightCircle style="width: 1.5rem; height: 1.5rem"/>
            </Button>
        </div>
    </div>

    <ProductBasket></ProductBasket>
</div>

