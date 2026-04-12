package com.example.fastmart;

import java.util.ArrayList;

public class ProductList {

    private static ArrayList<Product> cachedProducts = null;

    public static ArrayList<Product> getProducts() {
        if (cachedProducts == null) {
            cachedProducts = new ArrayList<>();

            // ── Headphones & Earbuds ──────────────────────────────────────────
            cachedProducts.add(new Product(1, "Sony WH-1000XM4 Black", "Headphones",
                    "The Sony WH-1000XM4 redefines what it means to have a premium listening experience. " +
                            "Featuring industry-leading noise cancellation powered by the HD Noise Cancelling Processor QN1, " +
                            "these headphones block out the world so you can focus on your music. " +
                            "With up to 30 hours of battery life, you can listen all day without interruption. " +
                            "The speak-to-chat technology automatically pauses playback when you start a conversation. " +
                            "Multipoint connection lets you pair with two Bluetooth devices simultaneously. " +
                            "Touch controls on the ear cup make it easy to manage calls, volume, and playback. " +
                            "The premium leather ear cushions provide exceptional comfort for long listening sessions. " +
                            "Supports LDAC for high-resolution audio streaming up to 990kbps.",
                    249.99, 349.99, R.drawable.headphone_black170, false, true));

            cachedProducts.add(new Product(2, "Sony WH-1000XM4 Beige", "Headphones",
                    "The Sony WH-1000XM4 in elegant Beige brings style and substance together effortlessly. " +
                            "Powered by the HD Noise Cancelling Processor QN1, it delivers class-leading noise cancellation. " +
                            "Adaptive Sound Control automatically adjusts ambient sound settings based on your activity. " +
                            "The 40mm drivers deliver rich, detailed sound across the full frequency range. " +
                            "Wear detection pauses playback automatically when you take the headphones off. " +
                            "Foldable design makes it easy to carry in the included premium carry case. " +
                            "The built-in microphone array ensures crystal-clear hands-free calling. " +
                            "Quick charge provides 5 hours of playback from just 10 minutes of charging. " +
                            "Compatible with Amazon Alexa and Google Assistant for voice control.",
                    249.99, 349.99, R.drawable.headphone_beige170, false, false));

            cachedProducts.add(new Product(3, "Apple AirPods Pro", "Earbuds",
                    "Apple AirPods Pro deliver an extraordinary listening experience in a compact design. " +
                            "Active Noise Cancellation removes background noise so you can focus on what matters most. " +
                            "Transparency mode lets outside sound in so you can hear the world around you when needed. " +
                            "The Adaptive Audio feature intelligently blends noise cancellation and transparency. " +
                            "A custom-built H2 chip drives the advanced audio engine for incredibly detailed sound. " +
                            "Personalized Spatial Audio with dynamic head tracking places sound all around you. " +
                            "The MagSafe charging case delivers over 30 hours of total battery life. " +
                            "IPX4 water resistance protects against sweat and water splashes. " +
                            "Touch controls let you manage audio and calls right from the ear cup.",
                    189.99, 249.99, R.drawable.airpods_pro, false, true));

            cachedProducts.add(new Product(4, "Samsung Galaxy Buds 2", "Earbuds",
                    "Samsung Galaxy Buds 2 offer premium sound and comfort in a lightweight design. " +
                            "Active Noise Cancellation with three microphones filters out unwanted background noise. " +
                            "The two-way speaker system — woofer and tweeter — delivers rich, balanced audio. " +
                            "Ambient Sound mode keeps you aware of your environment at all times. " +
                            "Auto Switch seamlessly transitions audio between your Galaxy devices. " +
                            "Up to 20 hours of total battery life with the compact charging case included. " +
                            "IPX7 water resistance makes them suitable for workouts and outdoor use. " +
                            "360 Audio with head tracking creates an immersive surround sound experience. " +
                            "The ergonomic design with three ear tip sizes ensures a secure, comfortable fit.",
                    89.99, 149.99, R.drawable.galaxybuds, false, false));

            // ── Microphones ───────────────────────────────────────────────────
            cachedProducts.add(new Product(5, "RØDE PodMic", "Microphones",
                    "The RØDE PodMic is a broadcast-quality dynamic microphone built for podcasting and streaming. " +
                            "Its cardioid polar pattern focuses on your voice and rejects background noise effectively. " +
                            "Internal pop filter reduces plosives without sacrificing vocal clarity or warmth. " +
                            "The rugged all-metal construction ensures durability for years of heavy use. " +
                            "An integrated swing mount allows flexible positioning on any standard boom arm. " +
                            "Frequency response is tailored specifically for voice, delivering punchy and articulate audio. " +
                            "Compatible with the RØDE RødeCaster Pro for professional podcast production setups. " +
                            "The tight cardioid pickup pattern minimizes room reflections and ambient noise. " +
                            "Used by top podcasters and streamers worldwide for its outstanding sound quality.",
                    108.20, 199.99, R.drawable.mic128, false, true));

            cachedProducts.add(new Product(6, "Blue Yeti USB Mic", "Microphones",
                    "The Blue Yeti is one of the most popular USB microphones for creators and professionals. " +
                            "Four selectable pickup patterns — cardioid, bidirectional, omnidirectional, and stereo — offer versatility. " +
                            "The built-in headphone amplifier with zero-latency monitoring lets you hear yourself clearly. " +
                            "Plug-and-play USB connectivity works instantly with Mac and Windows with no drivers needed. " +
                            "The gain control knob lets you adjust sensitivity to suit your recording environment. " +
                            "Mute button with LED indicator makes it easy to silence yourself during calls or streams. " +
                            "The sturdy desktop stand keeps the mic stable while allowing angle adjustments. " +
                            "Captures studio-quality 16-bit, 48kHz audio for podcasts, vocals, and instruments. " +
                            "Compatible with Blue SHERPA software for advanced DSP effects and EQ control.",
                    99.99, 129.99, R.drawable.mic128, false, false));

            // ── Monitors ──────────────────────────────────────────────────────
            cachedProducts.add(new Product(7, "Samsung 27\" Monitor", "Monitors",
                    "The Samsung 27-inch 4K UHD monitor brings stunning clarity to everything you do. " +
                            "The IPS panel delivers accurate colors with wide viewing angles up to 178 degrees. " +
                            "4K UHD resolution with over 8 million pixels ensures incredibly sharp and detailed visuals. " +
                            "AMD FreeSync technology eliminates screen tearing for smooth gaming and video playback. " +
                            "The slim bezel design makes it ideal for multi-monitor productivity setups. " +
                            "HDR10 support provides brighter highlights and deeper shadows for vivid content. " +
                            "Height, tilt, and pivot adjustments let you customize the viewing position with ease. " +
                            "Eye Saver Mode reduces blue light emission to minimize eye fatigue during long sessions. " +
                            "Multiple inputs including HDMI and DisplayPort support a wide range of devices.",
                    299.99, 449.99, R.drawable.ic_launcher_foreground, false, true));

            cachedProducts.add(new Product(8, "LG UltraWide 34\"", "Monitors",
                    "The LG 34-inch UltraWide monitor transforms your workflow with its expansive 21:9 display. " +
                            "QHD resolution at 3440x1440 delivers sharp, detailed visuals across the wide canvas. " +
                            "144Hz refresh rate with 1ms response time ensures buttery-smooth gaming performance. " +
                            "NVIDIA G-Sync and AMD FreeSync Premium support eliminate tearing and stuttering. " +
                            "The curved IPS panel wraps around your field of vision for an immersive experience. " +
                            "DCI-P3 98% color gamut coverage ensures professional-grade color accuracy throughout. " +
                            "Picture-by-Picture mode lets you display two full HD sources side by side simultaneously. " +
                            "USB-C connectivity with power delivery allows single-cable laptop connections. " +
                            "The ergonomic stand supports height, tilt, and pivot adjustments for all-day comfort.",
                    379.99, 499.99, R.drawable.lgmonitor, false, false));

            // ── Laptops & Tablets ─────────────────────────────────────────────
            cachedProducts.add(new Product(9, "MacBook Pro 14\"", "Laptops",
                    "The MacBook Pro 14-inch with M3 chip is the most powerful Mac laptop ever built. " +
                            "The Apple M3 chip delivers breakthrough CPU and GPU performance with incredible efficiency. " +
                            "The Liquid Retina XDR display features ProMotion adaptive refresh up to 120Hz. " +
                            "16GB unified memory ensures smooth multitasking across demanding professional applications. " +
                            "512GB SSD provides blazing-fast storage with read speeds up to 7.4GB per second. " +
                            "Up to 22 hours of battery life means you can work all day and into the evening. " +
                            "The advanced thermal system sustains peak performance even under heavy workloads. " +
                            "Three Thunderbolt 4 ports, HDMI, SD card slot, and MagSafe for versatile connectivity. " +
                            "The studio-quality three-mic array captures clear audio for calls and recordings.",
                    1599.99, 1999.99, R.drawable.ic_launcher_foreground, false, true));

            cachedProducts.add(new Product(10, "Dell XPS 15", "Laptops",
                    "The Dell XPS 15 is the ultimate Windows laptop for creators and power users alike. " +
                            "Powered by an Intel Core i7 processor for fast, responsive performance in every task. " +
                            "The 15.6-inch OLED display delivers vivid colors and deep blacks with 100% DCI-P3 coverage. " +
                            "16GB DDR5 RAM ensures smooth multitasking between creative and productivity applications. " +
                            "512GB NVMe SSD provides fast storage with quick boot and load times throughout. " +
                            "NVIDIA GeForce RTX 4060 GPU handles demanding graphics, video editing, and gaming. " +
                            "The precision-engineered CNC aluminum chassis is both lightweight and incredibly durable. " +
                            "Thunderbolt 4, USB-A, SD card reader, and headphone jack cover all connectivity needs. " +
                            "The four-speaker Waves MaxxAudio system delivers rich, room-filling sound quality.",
                    999.99, 1299.99, R.drawable.dellxps, false, false));

            cachedProducts.add(new Product(11, "iPad Air", "Tablets",
                    "The iPad Air with M1 chip brings desktop-class performance to a thin, portable form factor. " +
                            "The 10.9-inch Liquid Retina display delivers True Tone and P3 wide color for stunning visuals. " +
                            "Apple M1 chip with 8-core CPU and 8-core GPU handles any task with remarkable speed. " +
                            "USB-C connector supports fast data transfer and connection to a wide range of accessories. " +
                            "Compatible with Apple Pencil 2nd generation and Magic Keyboard for enhanced productivity. " +
                            "The 12MP ultra-wide front camera supports Center Stage for always-centered video calls. " +
                            "Available in five gorgeous finishes to match your personal style and preferences. " +
                            "Up to 256GB storage gives you room for all your apps, photos, and creative projects. " +
                            "All-day battery life of up to 10 hours keeps you going from morning to night.",
                    749.99, 899.99, R.drawable.ipadair, false, false));

            // ── Phones ────────────────────────────────────────────────────────
            cachedProducts.add(new Product(12, "Samsung Galaxy S24", "Phones",
                    "The Samsung Galaxy S24 sets a new standard for Android smartphones with AI-powered features. " +
                            "The 6.2-inch Dynamic AMOLED display with 120Hz adaptive refresh rate is stunning in every light. " +
                            "Snapdragon 8 Gen 3 processor delivers top-tier performance for gaming and multitasking. " +
                            "The 50MP triple camera system captures professional-quality photos and 8K video effortlessly. " +
                            "Galaxy AI features include live translation, note assist, and generative photo editing. " +
                            "IP68 water resistance means it can survive submersion up to 1.5 meters for 30 minutes. " +
                            "The Armor Aluminum frame and Corning Gorilla Glass Victus 2 protect against drops and scratches. " +
                            "7 years of OS upgrades and security updates ensure long-term support and reliability. " +
                            "Wireless PowerShare lets you charge other devices directly from your phone's battery.",
                    799.99, 999.99, R.drawable.ic_launcher_foreground, false, true));

            cachedProducts.add(new Product(13, "iPhone 15", "Phones",
                    "The iPhone 15 features a stunning design with a durable color-infused back glass finish. " +
                            "The 6.1-inch Super Retina XDR display with Dynamic Island delivers an immersive experience. " +
                            "The A16 Bionic chip, with a 6-core CPU and 5-core GPU, powers everything effortlessly. " +
                            "The 48MP main camera with 100% Focus Pixels captures sharp, detailed photos in any light. " +
                            "USB-C connector supports fast charging and transfer speeds for modern accessories. " +
                            "Roadside Assistance via satellite keeps you connected even without cellular coverage. " +
                            "Advanced privacy features including App Tracking Transparency keep your data safe. " +
                            "The Ceramic Shield front cover is tougher than any smartphone glass available today. " +
                            "Up to 20 hours of video playback on a single charge keeps you going all day long.",
                    899.99, 1099.99, R.drawable.iphone15, false, false));

            // ── Accessories ───────────────────────────────────────────────────
            cachedProducts.add(new Product(14, "Logitech MX Master 3", "Accessories",
                    "The Logitech MX Master 3 is the ultimate mouse for professional creators and power users. " +
                            "The MagSpeed electromagnetic scroll wheel scrolls 1000 lines per second with precise control. " +
                            "Connects to up to three devices simultaneously and switches between them with one button. " +
                            "The ergonomic sculpted shape supports your hand for extended use without fatigue. " +
                            "The thumb wheel enables horizontal scrolling for wide spreadsheets and timelines. " +
                            "USB-C quick charging gives you 3 hours of use from just one minute of charging. " +
                            "Works on virtually any surface including glass with the Darkfield high-precision sensor. " +
                            "Customizable buttons and scroll wheel can be configured per app using Logi Options+. " +
                            "Compatible with macOS and Windows with gesture support and app-specific profiles.",
                    79.99, 99.99, R.drawable.logitechmx, false, false));

            cachedProducts.add(new Product(15, "Mechanical Keyboard", "Accessories",
                    "This TKL mechanical keyboard delivers a premium typing experience for work and gaming. " +
                            "Brown switches offer a tactile bump with each keystroke without being overly loud. " +
                            "Per-key RGB backlighting with 16.8 million colors creates a vibrant, personalized setup. " +
                            "The tenkeyless layout saves desk space while keeping all essential keys within reach. " +
                            "Double-shot PBT keycaps resist shine and wear for long-lasting legends and durability. " +
                            "Programmable macros and key remapping give you full control over your workflow. " +
                            "The detachable USB-C cable makes it easy to transport and swap between workstations. " +
                            "N-key rollover and anti-ghosting support ensure every keypress is registered accurately. " +
                            "The aluminum top plate adds rigidity and a premium feel to the overall build quality.",
                    69.99, 89.99, R.drawable.ic_launcher_foreground, false, false));

            cachedProducts.add(new Product(16, "USB-C Hub 7-in-1", "Accessories",
                    "This 7-in-1 USB-C hub expands your laptop's connectivity with all the ports you need. " +
                            "HDMI output supports up to 4K 60Hz for crisp and smooth external display connections. " +
                            "Three USB 3.0 ports provide fast data transfer at speeds up to 5Gbps simultaneously. " +
                            "The SD and microSD card reader supports UHS-I speeds for fast photo and video transfers. " +
                            "USB-C Power Delivery pass-through supports up to 100W for fast laptop charging. " +
                            "The compact aluminum housing dissipates heat and keeps the hub running reliably. " +
                            "Plug-and-play design requires no drivers and works with Mac, Windows, and ChromeOS. " +
                            "The braided cable resists fraying and tangling for long-term reliability and durability. " +
                            "Compatible with all USB-C laptops including MacBook, Dell XPS, and Surface devices.",
                    29.99, 49.99, R.drawable.ic_launcher_foreground, false, false));

            cachedProducts.add(new Product(17, "Webcam 4K", "Accessories",
                    "This 4K webcam delivers crystal-clear video for professional meetings and content creation. " +
                            "Ultra HD 4K resolution at 30fps captures every detail with stunning sharpness and clarity. " +
                            "The built-in dual microphone array with noise cancellation picks up your voice clearly. " +
                            "Advanced autofocus with face tracking keeps you sharp even when you move around. " +
                            "The wide 90-degree field of view captures more of your environment for group calls. " +
                            "Automatic low-light correction ensures you look great even in poorly lit environments. " +
                            "Privacy shutter physically blocks the lens when the webcam is not in use for security. " +
                            "Plug-and-play USB-A and USB-C compatibility works with all major video conferencing apps. " +
                            "The flexible clip mount attaches securely to monitors, laptops, and tripods with ease.",
                    99.99, 149.99, R.drawable.ic_launcher_foreground, false, false));

            // ── Storage ───────────────────────────────────────────────────────
            cachedProducts.add(new Product(18, "Portable SSD 1TB", "Storage",
                    "This portable 1TB SSD delivers blazing-fast transfer speeds for professionals on the go. " +
                            "USB 3.2 Gen 2 interface achieves sequential read speeds of up to 1050MB per second. " +
                            "The compact, pocket-sized design makes it incredibly easy to carry wherever you go. " +
                            "Shock-resistant construction protects your data from accidental drops up to 1.8 meters. " +
                            "AES 256-bit hardware encryption keeps your sensitive files safe and secure at all times. " +
                            "Compatible with PC, Mac, Android, iPad Pro, and gaming consoles for universal use. " +
                            "The included USB-C to USB-C and USB-C to USB-A cables cover all connection scenarios. " +
                            "Dynamic thermal management prevents overheating during sustained read and write operations. " +
                            "Backed by a 5-year limited warranty for long-term peace of mind and reliability.",
                    89.99, 119.99, R.drawable.ic_launcher_foreground, false, true));

            // ── Clothing ──────────────────────────────────────────────────────
            cachedProducts.add(new Product(19, "Jeans", "Clothing",
                    "These slim fit denim jeans combine classic style with modern comfort for everyday wear. " +
                            "Crafted from premium stretch denim that moves with you throughout the day comfortably. " +
                            "The slim fit silhouette flatters all body types without feeling restrictive or tight. " +
                            "Reinforced stitching at stress points ensures long-lasting durability wash after wash. " +
                            "Five-pocket design provides practical storage while maintaining a clean, tailored look. " +
                            "Available in multiple washes from light indigo to deep midnight blue for every style. " +
                            "The mid-rise waistband sits comfortably at your natural waist for all-day wear. " +
                            "Machine washable fabric retains its color and shape even after repeated washing cycles. " +
                            "Versatile enough to dress up with a blazer or dress down with a casual t-shirt.",
                    39.99, 59.99, R.drawable.ic_launcher_foreground, false, false));

            cachedProducts.add(new Product(20, "Casual Clothes", "Clothing",
                    "This everyday casual wear set is designed for comfort, style, and effortless versatility. " +
                            "Made from soft breathable cotton blend fabric that keeps you cool and comfortable all day. " +
                            "The relaxed fit provides ease of movement for everything from errands to weekend outings. " +
                            "Minimalist design with clean lines makes it easy to mix and match with any wardrobe. " +
                            "Pre-shrunk fabric ensures the fit stays consistent even after multiple wash cycles. " +
                            "Available in a range of neutral and seasonal colors to suit any personal style. " +
                            "Reinforced seams and hems prevent fraying and maintain a neat appearance over time. " +
                            "Suitable for a wide range of casual occasions from coffee runs to casual Fridays at work. " +
                            "Lightweight and packable, making it a great choice for travel and weekend getaways.",
                    49.99, 69.99, R.drawable.ic_launcher_foreground, false, false));

            cachedProducts.add(new Product(21, "Hoodie", "Clothing",
                    "This pullover fleece hoodie is your go-to layer for cool weather and casual comfort. " +
                            "Made from a heavyweight cotton-polyester blend that is soft, warm, and incredibly cozy. " +
                            "The brushed fleece interior traps heat effectively while remaining lightweight on the body. " +
                            "A spacious kangaroo pocket keeps your hands warm and holds small essentials conveniently. " +
                            "Ribbed cuffs and hem provide a snug fit that keeps cold air from sneaking in. " +
                            "The adjustable drawstring hood can be tightened for extra coverage in windy conditions. " +
                            "Available in a wide palette of colors from classic neutrals to bold seasonal shades. " +
                            "Unisex sizing and relaxed cut make it a comfortable and versatile choice for everyone. " +
                            "Machine washable and resistant to pilling for long-lasting softness and appearance.",
                    34.99, 54.99, R.drawable.ic_launcher_foreground, false, false));

            cachedProducts.add(new Product(22, "V-Neck T-Shirt", "Clothing",
                    "This cotton slim-fit v-neck t-shirt is a wardrobe staple for any casual or smart-casual look. " +
                            "Made from 100% ring-spun cotton for a supremely soft feel against the skin all day. " +
                            "The v-neck collar adds a modern, flattering touch that works well under jackets or alone. " +
                            "Slim fit cut follows the body's natural contour without being uncomfortably tight. " +
                            "Pre-shrunk fabric ensures consistent sizing and shape even after repeated washes. " +
                            "Available in a full spectrum of colors from classic white to rich jewel tones. " +
                            "Breathable fabric keeps you cool and fresh during warm weather or indoor activities. " +
                            "Double-needle hem stitching adds durability and maintains a clean, polished finish. " +
                            "Versatile enough to pair with jeans, chinos, shorts, or layer under a shirt or jacket.",
                    19.99, 29.99, R.drawable.ic_launcher_foreground, false, false));

            cachedProducts.add(new Product(23, "Winter Clothes", "Clothing",
                    "This warm winter jacket and scarf set is designed to keep you cozy through the coldest days. " +
                            "The jacket features a thick quilted outer shell that locks in warmth without added bulk. " +
                            "Water-resistant coating repels light rain and snow to keep you dry in winter conditions. " +
                            "Insulated lining provides excellent thermal retention even in freezing temperatures below zero. " +
                            "Multiple zip pockets keep your phone, wallet, and keys organized and easily accessible. " +
                            "The matching scarf is made from soft wool blend fabric that is gentle against the skin. " +
                            "Adjustable cuffs and hem allow you to seal out cold drafts for maximum warmth retention. " +
                            "Available in classic winter colorways including charcoal, navy, burgundy, and forest green. " +
                            "Stylish enough to wear to work and functional enough for outdoor winter activities.",
                    79.99, 109.99, R.drawable.ic_launcher_foreground, false, false));

            // ── Footwear ──────────────────────────────────────────────────────
            cachedProducts.add(new Product(24, "Nike Shoes Black", "Footwear",
                    "These Nike Air Max running shoes in black deliver comfort and performance for every run. " +
                            "The iconic Air Max cushioning unit provides responsive impact absorption with every step. " +
                            "Engineered mesh upper offers lightweight breathability to keep your feet cool and fresh. " +
                            "The rubber outsole with multidirectional traction pattern performs well on various surfaces. " +
                            "Foam midsole provides a plush, cushioned ride for long-distance running and daily wear. " +
                            "Reinforced heel counter provides stability and prevents unwanted foot slippage inside the shoe. " +
                            "Padded collar and tongue add comfort while providing a secure, lockdown fit during activity. " +
                            "Reflective details increase visibility during low-light morning or evening runs outdoors. " +
                            "Available in half sizes for a precise fit that supports your foot throughout the day.",
                    89.99, 119.99, R.drawable.ic_launcher_foreground, false, true));

            // ── Home ──────────────────────────────────────────────────────────
            cachedProducts.add(new Product(25, "Desk Lamp LED", "Home",
                    "This LED desk lamp combines elegant design with smart functionality for your workspace. " +
                            "Touch-sensitive dimmer allows you to adjust brightness across five levels with a light tap. " +
                            "Five color temperature modes range from warm white to cool daylight for any task or mood. " +
                            "Built-in USB charging port lets you charge your phone or tablet directly from the lamp. " +
                            "Flexible gooseneck arm rotates 360 degrees to direct light exactly where you need it. " +
                            "Energy-efficient LED bulbs consume up to 80% less power than traditional incandescent bulbs. " +
                            "Memory function remembers your last brightness and color temperature setting automatically. " +
                            "Eye-care technology reduces flicker and minimizes blue light to protect your vision long-term. " +
                            "Sleek minimalist design complements any desk setup, home office, or bedside table perfectly.",
                    24.99, 39.99, R.drawable.ic_launcher_foreground, false, false));
        }
        return cachedProducts;
    }

    public static ArrayList<Product> getDeals() {
        ArrayList<Product> deals = new ArrayList<>();
        for (Product p : getProducts()) {
            if (p.onDeal()) deals.add(p);
        }
        return deals;
    }

    public static ArrayList<Product> getRecommended() {
        ArrayList<Product> recommended = new ArrayList<>();
        for (Product p : getProducts()) {
            if (!p.onDeal()) recommended.add(p);
        }
        return recommended;
    }

    public static ArrayList<Product> getFavourites() {
        ArrayList<Product> favs = new ArrayList<>();
        for (Product p : getProducts()) {
            if (p.isFavourite()) favs.add(p);
        }
        return favs;
    }
}