package amidst.mojangapi.file.json.player;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import amidst.documentation.GsonConstructor;
import amidst.documentation.Immutable;
import amidst.mojangapi.file.json.JsonReader;

@Immutable
public class PropertyJson {
	private volatile String name;
	private volatile String value;

	@GsonConstructor
	public PropertyJson() {
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	// TODO: @skiphs we need a base64 decoder to read this api, but it comes
	// only with java8. should we use java8 or another base64 decoder?
	public String getDecodedValue() {
		return new String(Base64.getDecoder().decode(
				value.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
	}

	public TexturesPropertyJson tryDecodeTexturesProperty() {
		if (name.equals("textures")) {
			return JsonReader.read(getDecodedValue(),
					TexturesPropertyJson.class);
		} else {
			return null;
		}
	}
}
