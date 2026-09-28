# Immich TV private pilot

This fork is based on `giejay/Immich-Android-TV` at commit
`fc7d773d6dd164978d32e203b25c7056473e54ae` and retains its GPLv3 license.
It uses a separate package ID, `org.lastdomovoi.immichtv.private`.

Changes for the NAS2 pilot:

- Removed Firebase Analytics, Crashlytics, Google Services, phone sign-in via the
  developer's website, and Google Play billing.
- Stopped embedding the Immich API key in video or thumbnail URLs. The internal
  player and image loader send it as an `x-api-key` header. External video player
  and Android TV home screen channels are disabled because they cannot use this
  header without additional authenticated proxy work.
- Refuse plaintext API key storage when Android Keystore is unavailable. The app
  does not back up its data or transfer it to another device.
- Removed the option to disable TLS certificate verification and removed HTTP
  response body logging. The key entry field is masked.

The pilot server currently uses LAN HTTP. On that path, the API key and media are
unencrypted on the network. Use a disposable, narrowly scoped TV account and key
for testing. Production use with a real family library needs a trusted HTTPS
endpoint, a signed release APK, device smoke tests, and network-traffic review.

Do not install this fork from Google Play: that listing serves the upstream app.
Do not use upstream APKs or the developer's phone sign-in site with NAS2 accounts.
