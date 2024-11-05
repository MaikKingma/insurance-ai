<script>
import {Button} from "$lib/components/ui/button";
</script>

<style>
    @keyframes rainbowlogo {
        0% {filter:  sepia(0.5) brightness(0.5) hue-rotate(0deg)}
        10% {filter: sepia(0.5) brightness(0.5) hue-rotate(36deg)}
        20% {filter: sepia(0.5) brightness(0.5) hue-rotate(72deg)}
        30% {filter: sepia(0.5) brightness(0.5) hue-rotate(180deg)}
        40% {filter: sepia(0.5) brightness(0.5) hue-rotate(144deg)}
        50% {filter: sepia(0.5) brightness(0.5) hue-rotate(180deg)}
        60% {filter: sepia(0.5) brightness(0.5) hue-rotate(216deg)}
        70% {filter: sepia(0.5) brightness(0.5) hue-rotate(252deg)}
        80% {filter: sepia(0.5) brightness(0.5) hue-rotate(288deg)}
        90% {filter: sepia(0.5) brightness(0.5) hue-rotate(384deg)}
        100% {filter: sepia(0.5) brightness(0.5) hue-rotate(360deg)}
    }

    @keyframes zoom {
        0% { transform: scale(1) rotate(0deg) translate(0, 0); }
        100% { transform: scale(2) rotate(10deg) translate(-6%, 15%); }
    }

    .hero-foreground {
        background-image: url("/home-foreground-couple.png");
        background-size: cover;
        animation: zoom 120s infinite ease-in-out alternate;
    }
    .hero-background {
        background-image: url("/home-background-couch.png");
        background-size: cover;
        animation: rainbowlogo 10s infinite;
    }
    .one-third {
        height: 50dvh
    }
</style>

<section class="one-third grid justify-items-center content-center p-4 relative overflow-hidden">
    <div class="w-full h-full absolute hero-background" ></div>
    <div class="w-full h-full absolute hero-foreground" ></div>
    <div class=" absolute flex w-full h-full justify-items-center p-16">
        <header class="pl-2 pt-2 lg:w-1/2  xl:pt-20 xl:pl-20">
            <h2 class="scroll-m-20 pb-2 text-2xl text-white font-semibold tracking-tight transition-colors first:mt-0">
                Insurance Perfectly Tailored for You
            </h2>
            <p class="scroll-m-20 text-4xl font-semibold text-white tracking-tight">
                A.I. finds the best policy
            </p>
            <Button class="mt-6" href="/sign-up">Get started now!</Button>
        </header>
    </div>
</section>
