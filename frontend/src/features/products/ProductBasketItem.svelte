<script lang="ts">
    import type {ProductView} from "$lib/api";
    import {fly} from "svelte/transition";
    const data: {product:ProductView} = $props()
</script>


<div in:fly={{ y: 100 }} class="border border-grey-500 rounded-md shadow overflow-hidden p-4">
    <div class="flex items-center justify-between gap-2 text-xs pb-2">
        <span class="flex items-center gap-2">
        {#if data.product.coverageCategory === "PREMIUM"}
            <span class="inline-block px-2 py-2 bg-orange-300 rounded-full"></span>
        {:else if data.product.coverageCategory === "BASIC"}
            <span class="inline-block px-2 py-2 bg-blue-500 rounded-full"></span>
        {:else if data.product.coverageCategory === "ADVANCED"}
            <span class="inline-block px-2 py-2 bg-yellow-800 rounded-full"></span>
        {:else if data.product.coverageCategory === "INTERMEDIATE"}
            <span class="inline-block px-2 py-2 bg-gray-500 rounded-full"></span>
        {/if}
        {data.product.coverageCategory}
        </span>
        <span>{data.product.category}</span>
    </div>
    <h4 class="text-lg bold">{data.product.name}</h4>
    <p class="text-sm text-blue-300 pb-2">{data.product.coverageDescription}</p>
<!--    <div>{data.product.category}</div>-->
    {#if data.product.price}
        <div class="flex items-center justify-between gap-2">
            {#each data.product.price as price}
                <div>{price.amount?.toFixed(2)}€ {price.paymentCycle}</div>
            {/each}
        </div>
    {/if}
</div>
