import { useState } from "react";

function SearchForm({ onSubmit }) {
  const [search, setSearch] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(search);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        name="pokemon"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      <button type="submit">Search</button>
    </form>
  );
}

export default SearchForm;
