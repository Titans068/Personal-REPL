function deflate(arr) {
    return pako.deflateRaw(arr, {
        "level": 9
    });
}

function encode(str) {
    const bytes = new TextEncoder("utf-8").encode(str);
    return deflate(bytes);
}

const TIO = {
    run:
        async function run(code, input, lang) {
            const encoder = new TextEncoder("utf-8");
            const length = encoder.encode(code).length;
            const iLength = encoder.encode(input).length;
            //  Vlang\u00001\u0000{language}\u0000F.code.tio\u0000{# of bytes in code}\u0000{code}F.input.tio\u0000{length of input}\u0000{input}Vargs\u0000{number of ARGV}{ARGV}\u0000R
            let rBody = "Vlang\x001\x00" + lang + "\x00F.code.tio\x00" + length + "\x00" + code + "F.input.tio\x00" + iLength + "\x00" + input + "Vargs\x000\x00R";
            rBody = encode(rBody);
            const fetched = await fetch("https://tio.run/cgi-bin/run/api/", {
                method: "POST",
                headers: {
                    "Content-Type": "text/plain;charset=utf-8"
                },
                body: rBody
            });
            const read = (await fetched.body.getReader().read()).value;
            const text = new TextDecoder('utf-8').decode(read);
            return text.slice(16).split(text.slice(0, 16));
        }
}

