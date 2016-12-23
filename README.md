# ChatStringParser
## Parse Emoticons, Links and Mentions with ease

1. **@mentions** - A way to mention a user. Always starts with an '@' and ends when hitting a
non-word character.
2. **Emoticons** - Are alphanumeric strings, no longer than 15 characters, contained in parenthesis.
3. **Links** - Any URLs contained in the message, along with the page's title.

####Output
Returns a JSONObject in the following format.

```
Input: "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016"
Return (string): {
    "mentions": [
    "bob",
    "john"
    ],
    "emoticons": [
    "success"
    ],
    "links": [
        {
            "url": "https://twitter.com/jdorfman/status/430511497475670016",
            "title": "Twitter / jdorfman: nice @littlebigdetail from ..."
        }

    ]
}

```